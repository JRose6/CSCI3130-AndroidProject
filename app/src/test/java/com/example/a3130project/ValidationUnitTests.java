package com.example.a3130project;

import com.example.a3130project.Helpers.EmailValidator;
import com.example.a3130project.Helpers.PasswordValidator;
import com.example.a3130project.Helpers.PrescriptionHelper;
import com.example.a3130project.model.Prescription;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ValidationUnitTests
{
	@Test
	public void password_invalid()
	{
		assertEquals(PasswordValidator.validPassword("password"), PasswordValidator.Strength.Invalid);
		assertEquals(PasswordValidator.validPassword("abc"), PasswordValidator.Strength.Invalid);
	}

	@Test
	public void password_weak()
	{
		assertEquals(PasswordValidator.validPassword("abccc3"), PasswordValidator.Strength.Weak);
		assertEquals(PasswordValidator.validPassword("Abcxxc"), PasswordValidator.Strength.Weak);
		assertEquals(PasswordValidator.validPassword("abcxc$"), PasswordValidator.Strength.Weak);
		assertEquals(PasswordValidator.validPassword("abcdefgkh"), PasswordValidator.Strength.Weak);
	}
	@Test
	public void password_medium()
	{
		assertEquals(PasswordValidator.validPassword("aBccc3"), PasswordValidator.Strength.Medium);
		assertEquals(PasswordValidator.validPassword("A$cxxc"), PasswordValidator.Strength.Medium);
		assertEquals(PasswordValidator.validPassword("abcxc$2"), PasswordValidator.Strength.Medium);
		assertEquals(PasswordValidator.validPassword("abcdefgkh1"), PasswordValidator.Strength.Medium);
		assertEquals(PasswordValidator.validPassword("abcdefgkhH"), PasswordValidator.Strength.Medium);
		assertEquals(PasswordValidator.validPassword("abcdefgkh$"), PasswordValidator.Strength.Medium);
	}
	@Test
	public void password_strong(){
		assertEquals(PasswordValidator.validPassword("abCwecch3"), PasswordValidator.Strength.Strong);
		assertEquals(PasswordValidator.validPassword("Abcxxcsd&"), PasswordValidator.Strength.Strong);
		assertEquals(PasswordValidator.validPassword("$aB23dh"), PasswordValidator.Strength.Strong);
		assertEquals(PasswordValidator.validPassword("abcdefgkh$2"), PasswordValidator.Strength.Strong);
	}
	@Test
	public void password_excellent()
	{
		assertEquals(PasswordValidator.validPassword("abCwecch3%%sad#1"), PasswordValidator.Strength.Excellent);
	}
	@Test
	public void email_valid(){
		assertEquals(EmailValidator.Status.Valid,EmailValidator.getEmail("user@dal.ca"));
	}
	@Test
	public void email_invalid(){
		assertEquals(EmailValidator.Status.Invalid,EmailValidator.getEmail("userdal.ca"));
	}
	@Test
	public void take_dosage(){
		Prescription p = new Prescription();
		p.setDosage(2);
		p.setRemainingMeds(10);
		PrescriptionHelper.takeDosage(p);
		assertEquals(8,p.getRemainingMeds());
		PrescriptionHelper.takeDosage(p);
		PrescriptionHelper.takeDosage(p);
		PrescriptionHelper.takeDosage(p);
		PrescriptionHelper.takeDosage(p);
		PrescriptionHelper.takeDosage(p);
		assertEquals(0,p.getRemainingMeds());
	}
	@Test
	public void refill_prescription(){
		Prescription p = new Prescription();
		p.setDosage(2);
		p.setRemainingMeds(0);
		p.setTotalMeds(20);
		p = PrescriptionHelper.refill(p);
		assertEquals(20, p.getRemainingMeds());
	}
}
