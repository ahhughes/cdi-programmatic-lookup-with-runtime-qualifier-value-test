package com.github.ahhughes.cdiprogrammaticlookupwithruntimequalifiervaluetest;

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
		MarcoPoloQualifer qualifier = injectionPoint.getAnnotated()
				.getAnnotation(MarcoPoloQualifer.class);
		System.out.println("qualifier==" + qualifier);
		System.out.println("qualifier.value()==" + qualifier.value());
		// return the other
		return MarcoPolo.MARCO.equals(qualifier.value()) ? MarcoPolo.POLO
				: MarcoPolo.MARCO;
	}

}
