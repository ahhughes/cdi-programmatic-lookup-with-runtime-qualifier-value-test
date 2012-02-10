package com.github.ahhughes.cdiprogrammaticlookupwithruntimequalifiervaluetest;

import javax.enterprise.util.AnnotationLiteral;


@SuppressWarnings("serial")
public class MarcoPoloQualiferLiteral extends
		AnnotationLiteral<MarcoPoloQualifer> implements MarcoPoloQualifer {

	public MarcoPolo value;	
	
	public MarcoPoloQualiferLiteral(MarcoPolo value){
		this.value = value;
	}

	@Override
	public MarcoPolo value() {
		return this.value;
	}

}
