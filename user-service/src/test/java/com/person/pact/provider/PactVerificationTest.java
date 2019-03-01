package com.person.pact.provider;

import au.com.dius.pact.provider.junit.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(SpringRestPactRunner.class)
@Provider("CatalogService")
@IgnoreNoPactsToVerify
@PactBroker(host = "localhost")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PactVerificationTest {

	@TestTarget
	public final Target target = new HttpTarget(8888);

	@Autowired
	private PersonRepository personRepository;

	@State({"default", "extra"})
	public void toDefaultState() {
		System.out.println("Now service in default state");
	}

	@State("Person with SSN(71039012345) exists")
	public void personWithIdExist() {
		personRepository.add(new Person(null, "Roger Test", "71039012345", null));
	}
}
