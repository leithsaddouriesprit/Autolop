package tn.esprit.autoloc.Config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* tn.esprit.autoloc.Services..*(..))")
    public Object surveillerExecution(ProceedingJoinPoint joinPoint) throws Throwable {
        String methode = joinPoint.getSignature().toShortString();
        long debut = System.currentTimeMillis();
        log.info("Début d'exécution : {}", methode);
        try {
            Object resultat = joinPoint.proceed();
            long duree = System.currentTimeMillis() - debut;
            log.info("Fin d'exécution : {}", methode);
            log.info("Durée : {} ms", duree);
            return resultat;
        } catch (Throwable ex) {
            log.error("Erreur dans {} : {}", methode, ex.getMessage());
            throw ex;
        }
    }

    @After("execution(* tn.esprit.autoloc.Services.VehiculeServiceImpl.*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        log.info("In method : {}", name);
    }

    @Around("execution(* tn.esprit.autoloc.Services.VehiculeServiceImpl.recupererVehicules(..))")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object obj = pjp.proceed();
        long elapsedTime = System.currentTimeMillis() - start;
        log.info("Method execution time: {} milliseconds.", elapsedTime);
        return obj;
    }
}
