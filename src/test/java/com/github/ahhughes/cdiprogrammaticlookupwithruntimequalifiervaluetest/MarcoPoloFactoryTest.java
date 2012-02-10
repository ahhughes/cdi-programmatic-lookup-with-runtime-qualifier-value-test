package com.github.ahhughes.cdiprogrammaticlookupwithruntimequalifiervaluetest;

import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import junit.framework.Assert;

import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ByteArrayAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MarcoPoloFactoryTest {

	@Inject
	@Any
	Instance<MarcoPolo> marcoPoloSource;
	
	private final MarcoPoloQualiferLiteral marcoLitteral = new MarcoPoloQualiferLiteral(MarcoPolo.MARCO);
	private final MarcoPoloQualiferLiteral poloLitteral = new MarcoPoloQualiferLiteral(MarcoPolo.POLO);

	@Deployment
	public static JavaArchive createTestArchive() {
		return ShrinkWrap
				.create(JavaArchive.class,"test.jar")
				.addClasses(Math.class, MarcoPolo.class,
						MarcoPoloFactory.class, MarcoPoloQualifer.class,
						MarcoPoloQualiferLiteral.class)
				.addAsManifestResource(new ByteArrayAsset("<beans/>".getBytes()),
						ArchivePaths.create("beans.xml"));
	}

	@Test
	public void testGetOtherForMarco() {
		//let's run it 1000 times to be sure. heeehehehe!
		for (int i = 0 ; i < 1000 ; i++){
			MarcoPolo shouldBePolo = marcoPoloSource.select(marcoLitteral).get();
			Assert.assertEquals(MarcoPolo.POLO, shouldBePolo);
			Assert.assertNotSame(marcoLitteral.value, shouldBePolo);
			
			MarcoPolo shouldBeMarco = marcoPoloSource.select(poloLitteral).get();
			Assert.assertEquals(MarcoPolo.POLO, shouldBeMarco);
			Assert.assertNotSame(marcoLitteral.value, shouldBeMarco);			
		}
	}

}
