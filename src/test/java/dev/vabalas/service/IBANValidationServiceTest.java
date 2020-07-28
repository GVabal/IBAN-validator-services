package dev.vabalas.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class IBANValidationServiceTest {

    @Autowired
    private IBANValidationService service;

    @Test
    void testValidateFormat() {
        assertTrue(service.validateFormat("MD24AG000225100013104168"));
        assertTrue(service.validateFormat("ST68000100010051840112"));
        assertTrue(service.validateFormat("FR142"));
        assertFalse(service.validateFormat("Not an IBAN number"));
        assertFalse(service.validateFormat("5000400440116243"));
        assertFalse(service.validateFormat("DKXX00400440116243"));
        assertFalse(service.validateFormat("AAAA"));
        assertFalse(service.validateFormat("AA01"));
    }

    @Test
    void testValidateCountry() {
        assertTrue(service.validateCountry("LT0000000"));
        assertTrue(service.validateCountry("SE0000000"));
        assertFalse(service.validateCountry("00SE0000000"));
        assertFalse(service.validateCountry("ZZ0000000"));
    }

    @Test
    void testValidateLength() {
        assertTrue(service.validateLength("IE29AIBK93115212345678"));
        assertTrue(service.validateLength("KW81CBKU0000000000001234560101"));
        assertFalse(service.validateLength("IE29AIBK93112345678"));
        assertFalse(service.validateLength("IE29AIBK931152123654845678"));
    }

    @Test
    void testValidateStructure() {
        assertTrue(service.validateStructure("BH67BMAG00001299123456"));
        assertTrue(service.validateStructure("TR330006100519786457841326"));
        assertFalse(service.validateStructure("XK0512D20123A5678906"));
        assertFalse(service.validateStructure("BY13N11B3600900000002Z00AB00"));
    }

    @Test
    void testValidateMod97() {
        assertTrue(service.validateMod97("BE68539007547034"));
        assertTrue(service.validateMod97("IT60X0542811101000000123456"));
        assertFalse(service.validateMod97("BE68539607547032"));
        assertFalse(service.validateMod97("IT60X0542811501000700123456"));
    }

    @Test
    void testValidateIBAN() {
        assertTrue(service.validateIBAN("MK07250120000058984"));
        assertFalse(service.validateIBAN("4545ABC4545"));
        assertFalse(service.validateIBAN("BB004545454545"));
        assertFalse(service.validateIBAN("IE29AIBK93112345678"));
        assertFalse(service.validateIBAN("BY13N11B3600900000002Z00AB00"));
        assertFalse(service.validateIBAN("BE68539607547032"));
    }

    @Test
    void testConvertIBANToNumber() {
        assertEquals("10111213", service.convertIBANToNumber("ABCD"));
        assertEquals("1011121356781234", service.convertIBANToNumber("1234ABCD5678"));
    }


}