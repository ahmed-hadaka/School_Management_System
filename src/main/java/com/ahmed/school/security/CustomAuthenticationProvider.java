package com.ahmed.school.security;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ahmed.school.models.Person;
import com.ahmed.school.repositories.PersonRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final PasswordEncoder passwordEncoder;
	private final PersonRepository personRepository;

	public CustomAuthenticationProvider(PasswordEncoder passwordEncoder, PersonRepository personRepository) {
		this.passwordEncoder = passwordEncoder;
		this.personRepository = personRepository;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = (String) authentication.getCredentials();
		UserDetails userDetails = isAuthenticatedUser(email, password);

		if (userDetails != null) {

			return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

		}
		throw new BadCredentialsException("");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private UserDetails isAuthenticatedUser(String email, String password) {
		Optional<Person> person = personRepository.findByEmail(email);

		// should setting the roles here again in order to access them using thymeleaf.
		if (person.isPresent() && passwordEncoder.matches(password, person.get().getPassword())) {
			UserDetails user = User.withUsername(person.get().getEmail()).password(password)
					.roles(person.get().getRole().getName()).build();
			return user;
		}
		return null;
	}

}
