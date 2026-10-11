# Atelier 4 — IoC, injection de dépendances et couche Service

## Périmètre et conventions du projet

Neuf interfaces et neuf implémentations sont présentes dans `tn.esprit.autoloc.Services`. Les huit services modifiables exposent cinq méthodes ; `IPaiement` expose seulement les deux lectures.

Les noms du projet existant sont conservés : `Entities`, `Repositories`, `Services`, interfaces `IClient`, `IContrat`, repositories `ClientRepo`, `ContratRepo`, entité `Vehicules`. Il s'agit d'une adaptation du support, qui utilise des packages minuscules, `I…Service`, `I…Repository` et des méthodes en anglais. Le comportement demandé est implémenté, mais le nommage n'est donc pas identique à la convention littérale du support.

| Support | Méthode dans ce projet |
| --- | --- |
| create(entite) | ajouterEntite(entite) |
| findById(id) | recupererEntiteById(id) |
| findAll() | recupererEntites() |
| update(id, entite) | updateEntite(entite), identifiant porté par l’objet |
| deleteById(id) | supprimerEntite(id) |

Chaque création refuse un identifiant déjà renseigné. Les lectures par identifiant et les suppressions lèvent `ResourceNotFoundException` lorsque la ressource n'existe pas. Une modification charge l'entité existante et recopie ses champs simples, sans changer l'identifiant ni les associations. Il s'agit d'une modification complète des champs simples, pas d'un PATCH : les champs obligatoires doivent être fournis.

Les services sont sans état métier par requête. `@Transactional(readOnly = true)` définit les lectures ; `@Transactional` sur les écritures englobe notamment le chargement et la modification dans une même transaction. Cette transaction ne remplace pas un mécanisme de contrôle des modifications concurrentes.

## A. Modes d'injection comparés

| Critère | Constructeur | Attribut |
| --- | --- | --- |
| Exemple | Champ final + @RequiredArgsConstructor | @Autowired sur le champ |
| Dépendance final | Oui | Non pour l'injection usuelle par attribut |
| Dépendance visible dans la signature du constructeur | Oui, constructeur généré par Lombok | Non |
| Utilisation hors Spring | Fournir explicitement le repository au constructeur | Un simple new laisse le repository à null |
| Recommandation | Mode retenu | Déconseillé |
| SonarQube for IDE | Pas d'injection par attribut | Règle S6813 attendue, à constater dans l'IDE |

Un constructeur unique n'a pas besoin de `@Autowired`. Le développeur doit lui fournir une dépendance ; Spring fournit le bean correspondant. Le mot-clé `final` ne constitue pas, à lui seul, une vérification contre un argument null fourni manuellement.

L'expérience temporaire d'injection par attribut reste à réaliser dans IntelliJ : remplacer le constructeur Lombok par `@Autowired` sur le champ, démarrer, relever le diagnostic puis restaurer le code final par constructeur.

## B. Services

| Service | Interface | Dépendance injectée | Mode et justification |
| --- | --- | --- | --- |
| AgenceServiceImpl | IAgence | AgenceRepo | Constructeur ; dépendance explicite et finale |
| ClientServiceImpl | IClient | ClientRepo | Constructeur ; dépendance explicite et finale |
| ContratServiceImpl | IContrat | ContratRepo | Constructeur ; dépendance explicite et finale |
| EmployeServiceImpl | IEmploye | EmployeRepo | Constructeur ; dépendance explicite et finale |
| EquipementServiceImpl | IEquipement | EquipementRepo | Constructeur ; dépendance explicite et finale |
| MaintenanceServiceImpl | IMaintenance | MaintenanceRepo | Constructeur ; dépendance explicite et finale |
| PaiementServiceImpl | IPaiement | PaiementRepo | Constructeur ; lectures uniquement |
| ReservationServiceImpl | IReservation | ReservationRepo | Constructeur ; dépendance explicite et finale |
| VehiculesServiceImpl | IVehicules | VehiculesRepo | Constructeur ; dépendance explicite et finale |

Spring Data fournit un proxy pour chaque interface repository. Le service dépend de cette abstraction et ne crée aucun repository avec `new`.

### Validations

Les données invalides déclenchent `IllegalArgumentException` avec un message explicite, à la création et à la modification.

| Entité | Règles adaptées aux attributs existants |
| --- | --- |
| Agence | Nom, ville, adresse et téléphone non vides |
| Client | Nom, prénom, email, téléphone et numéro de permis non vides ; date d'inscription présente |
| Employe | Nom et prénom non vides ; rôle présent |
| Equipement | Libellé non vide ; aucune quantité n'existe dans le modèle |
| Vehicules | Immatriculation, marque et modèle non vides ; catégorie, statut et tarif présents ; tarif >= 0 |
| Reservation | Dates et statut présents ; dateFin >= dateDebut |
| Contrat | Date de signature et montant présents ; montantTotal >= 0 |
| Maintenance | Dates présentes, description non vide ; dateFin >= dateDebut ; aucun coût n'existe dans le modèle |
| Paiement | Lecture seule ; pas de méthode d'écriture autonome |

Un objet d'entrée null est aussi refusé. Les contraintes d'unicité restent portées par la base. La validation déclarative et les opérations d'affectation entre entités appartiennent aux ateliers suivants.

La relation `Contrat.paiements` possède déjà `cascade = ALL`, donc la suppression JPA d'un contrat cascade vers ses paiements. La collection n'est pas remplacée dans `updateContrat`. Une réservation qui référence encore le contrat peut néanmoins empêcher sa suppression par contrainte de clé étrangère ; les opérations de désaffectation seront traitées avec la gestion des liens. Aucune méthode de suppression en lot n'est utilisée.

## C. Messages d'erreur du conteneur

### Message 1 : aucun IContratService trouvé

- Exception : `NoSuchBeanDefinitionException`, généralement enveloppée par `UnsatisfiedDependencyException` à l'injection dans le contrôleur.
- Bean consommateur : `ContratController` ; dépendance absente : l'interface du service contrat.
- Cause probable 1 : annotation `@Service` absente sur l'implémentation. Correction : l'ajouter.
- Cause probable 2 : implémentation hors du package scanné. Correction : la placer sous `tn.esprit.autoloc` ou configurer explicitement le scan.
- Vérifier également que la classe implémente l'interface demandée. Dans notre projet, l'interface s'appelle `IContrat`.

### Message 2 : deux implémentations de notification

- Exception : `NoUniqueBeanDefinitionException`, pouvant être enveloppée dans `UnsatisfiedDependencyException`.
- Bean consommateur : `NotificationController` ; candidats : `emailNotificateur` et `smsNotificateur`.
- Correction 1 : mettre `@Primary` sur la classe à privilégier par défaut.
- Correction 2 : mettre `@Qualifier("emailNotificateur")` sur le paramètre d'un constructeur explicite du contrôleur pour sélectionner ce bean à cet endroit.

### Message 3 : cycle ClientServiceImpl / ReservationServiceImpl

- Exception : `BeanCurrentlyInCreationException`, éventuellement enveloppée.
- Chaque constructeur exige une instance complète de l'autre service. Aucun des deux beans ne peut donc être construit en premier.
- Extraire l'opération commune dans un service d'orchestration qui dépend des deux services, sans dépendance réciproque. Ne pas masquer le défaut avec une injection par attribut ou l'activation des références circulaires.

## D. Qualité du code et SonarQube for IDE

Les anomalies ci-dessous ont été constatées par lecture du code du dépôt et corrigées. Elles ne sont pas présentées comme un rapport Sonar exécuté : SonarQube for IDE n'est pas disponible dans cet environnement. Il faut lancer l'analyse dans IntelliJ et confirmer les diagnostics/règles.

| Anomalie réellement présente avant correction | Règle attendue / explication | Correction effectuée |
| --- | --- | --- |
| ClientServiceImpl : imports HashSet et Set inutilisés | S1128 ; imports sans usage | Suppression des deux imports |
| IClient : imports Agence et Set inutilisés | S1128 ; types absents des signatures | Suppression des deux imports |
| PaiementRepo : import Equipement inutilisé | S1128 ; le repository porte sur Paiement | Suppression de l'import |
| VehiculesRepo : import Equipement inutilisé | S1128 ; le repository porte sur Vehicules | Suppression de l'import |

Ces quatre anomalies correspondent à une même famille de règle. Pour compléter les expériences demandées dans le support et observer d'autres règles :

1. Passer temporairement un service en injection par attribut ; relever S6813 si signalée ; restaurer l'injection par constructeur.
2. Remplacer temporairement `ResourceNotFoundException` par `RuntimeException` dans une lecture ; relever S112 si signalée ; restaurer l'exception métier.
3. Analyser les packages Services et exception, enregistrer les diagnostics réellement obtenus et leurs corrections. Les packages avec majuscule sont une divergence de convention conservée volontairement pour rester cohérent avec le projet actuel.

## E. Questions de compréhension

### 1. Où est le new de ContratServiceImpl et qui l'exécute ?

Il n'apparaît pas dans le code applicatif. Le conteneur Spring détecte `@Service`, résout `ContratRepo`, puis instancie le service via le constructeur généré par Lombok. Spring peut ensuite exposer un proxy pour les transactions.

### 2. Pourquoi un contrôleur dépend-il de l'interface ?

L'interface décrit les opérations disponibles sans imposer leur implémentation. Le contrôleur pourra recevoir une autre implémentation sans changer son propre code. Dans ce projet, il dépendra de `IContrat`.

### 3. Pourquoi un singleton doit-il rester sans état ?

Le même service peut traiter plusieurs requêtes simultanément. Un champ mutable `clientCourant` pourrait être remplacé par une seconde requête pendant le traitement de la première et associer une réservation au mauvais client. Les données d'une opération restent dans les paramètres et variables locales ; seul le repository est une dépendance finale partagée.

### 4. Pourquoi charger avant de modifier ?

Cela garantit l'existence de la ressource et permet de choisir explicitement les champs modifiables. On préserve l'identifiant et les associations au lieu de fusionner aveuglément un objet partiel. En particulier, une collection reçue vide ne remplace pas les paiements existants. Si une association utilise orphanRemoval, une telle substitution pourrait aussi supprimer des enfants.

### 5. Component / Bean, Primary / Qualifier et Paiement

- `@Component` annote une classe détectée automatiquement par le scan. `@Service` est une spécialisation métier de ce stéréotype.
- `@Bean` annote une méthode de configuration dont la valeur de retour est enregistrée comme bean, notamment pour un type tiers que l'on ne peut pas annoter.
- `@Primary` désigne le candidat à privilégier parmi plusieurs beans compatibles ; `@Qualifier` sélectionne un candidat à un point d'injection précis.
- `IPaiement` n'expose aucune écriture autonome, car les paiements sont gérés à travers leur contrat. Le repository Paiement sert ici aux lectures.

## Vérification et actions locales restantes

- Relecture des neuf couples interface/implémentation et des getters/setters réels des entités.
- Compilation Maven tentée avec Java 17 et le wrapper du dépôt. Elle est bloquée avant compilation : résolution DNS de `repo.maven.apache.org` impossible lors du téléchargement du parent Spring Boot 4.1.1. Le pom.xml existant est conservé. Aucun succès de compilation ou de démarrage n'est donc revendiqué.
- Pas de tests automatisés ajoutés : conformément au support, les services seront exercés via REST/Postman à l'atelier 5.
- Le démarrage avec la base MySQL locale et les expériences Sonar restent à réaliser sur le poste étudiant.

Depuis le dossier du projet, sous Windows :

```powershell
.\mvnw.cmd -DskipTests compile
.\mvnw.cmd spring-boot:run
```

Sous Linux :

```bash
bash mvnw -DskipTests compile
bash mvnw spring-boot:run
```

Vérifier que MySQL est lancé et que la configuration locale convient. Le journal doit contenir `Found 9 JPA repository interfaces` puis `Started AutolocApplication`. Conserver la capture du démarrage et les constats Sonar réellement obtenus pour le rendu.

## Contrôleurs REST et mise à jour par objet

Les huit contrôleurs complémentaires reprennent le modèle de ClientController dans le package `RestController`. Le contrôleur injecte l'interface du service avec `@RequiredArgsConstructor`.

Les huit services modifiables utilisent maintenant `updateEntite(entite)` : l'objet JSON doit inclure son identifiant (`idAgence`, `idClient`, `idContrat`, `idEmploye`, `idEquipement`, `idMaintenance`, `idReservation` ou `idVehicule`). La méthode valide les données, refuse un identifiant null, charge l'entité existante et copie uniquement ses champs simples. Les associations restent conservées. Cela remplace la signature précédente `modifierEntite(id, entite)`.

| Entité | Préfixe URL | Suffixe des opérations |
| --- | --- | --- |
| Agence | /api/Agence | Agence |
| Client | /api/Client | Client |
| Contrat | /api/Contrat | Contrat |
| Employe | /api/Employe | Employe |
| Equipement | /api/Equipement | Equipement |
| Maintenance | /api/Maintenance | Maintenance |
| Reservation | /api/Reservation | Reservation |
| Vehicules | /api/Vehicule | Vehicule |
| Paiement | /api/Paiement | Paiement, lectures uniquement |

Pour chaque entité modifiable : `POST /AddEntite`, `GET /GetAll`, `GET /GetById/{id}`, `PUT /updateEntite`, `DELETE /deleteEntite/{id}` sous son préfixe. Paiement expose uniquement les deux GET. Exemple : `PUT /api/Agence/updateAgence` avec `idAgence` dans le corps JSON et tous les champs simples obligatoires.
