package com.github.ahhughes.cdiprogrammaticlookupwithruntimequalifiervaluetest;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class MarcoPoloFactory {

	@Produces
	@MarcoPoloQualifer(MarcoPolo.MARCO)
	MarcoPolo getOther(InjectionPoint injectionPoint) {
		// The injectionPoint needs to supply ROCK, PAPER or SCISSORS...
		System.out.println("injectionPoint==" + injectionPoint);
		System.out.println("Invoked, injectionPoint.getQualifiers().size()=="
				+ injectionPoint.getQualifiers().size());
		for (Annotation annotation : injectionPoint.getQualifiers()){
			if (annotation instanceof MarcoPoloQualifer){
				MarcoPoloQualifer qualifier = (MarcoPoloQualifer) annotation;
				System.out.println("qualifier.value()=="+qualifier.value());
				return MarcoPolo.MARCO.equals(qualifier.value()) ? MarcoPolo.POLO
						: MarcoPolo.MARCO;
			}
		}
		throw new IllegalStateException("Weld should not do this! Qualifier "+MarcoPoloQualifer.class.getName()+" was not found, "+injectionPoint.getQualifiers());
	}

}
