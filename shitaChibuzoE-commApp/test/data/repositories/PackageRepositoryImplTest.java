package data.repositories;

import data.model.Package;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PackageRepositoryImplTest {

    private PackageRepository packageRepository;

    @BeforeEach
    public  void setUp(){
        packageRepository = new PackageRepositoryImpl();
    }

    @Test
    @DisplayName("Create new Package Test")
    public void saveOnePackage_countIsOneTest(){
        Package aPackage = new Package();
        assertEquals(0, packageRepository.count());
        packageRepository.save(aPackage);

        assertEquals(1, packageRepository.count());
    }

    @Test
    @DisplayName("Generate ID Test")
    public void saveOnePackage_IdIsOneTest(){
        Package aPackage = new Package();
        assertEquals(0, aPackage.getId());
        Package savedPackage = packageRepository.save(aPackage);
        assertEquals(1, savedPackage.getId());
    }

    @Test
    @DisplayName("Find by Id test")
    public void saveOnePackage_findPackageById_returnsSavedPackageTest(){
        Package aPackage = new Package();
        aPackage.setPayOnDelivery(true);
        packageRepository.save(aPackage);
        Package foundPackage = packageRepository.findById(1);
        assertEquals(1, foundPackage.getId());
        assertTrue(foundPackage.isPayOnDelivery());
        assertEquals(aPackage, foundPackage);
    }

    @Test
    @DisplayName("Update test ")
    public void saveTwoItemsWithSameId_countIsOneTest(){
        Package aPackage = new Package();
        aPackage.setWeightInGrammes(34);
        packageRepository.save(aPackage);
        Package savedAPackage = packageRepository.findById(1);
        assertEquals(aPackage, savedAPackage);

        Package secondPackage = new Package();
        secondPackage.setId(1);
        aPackage.setWeightInGrammes(22);

        packageRepository.save(secondPackage);
        Package foundPackage = packageRepository.findById(1);
        assertEquals(foundPackage,secondPackage);
        assertNotEquals(foundPackage, aPackage);
    }

}