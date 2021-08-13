package ke.co.macoz.realestate.waterbilling;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("ke.co.macoz.realestate.waterbilling");

        noClasses()
            .that()
            .resideInAnyPackage("ke.co.macoz.realestate.waterbilling.service..")
            .or()
            .resideInAnyPackage("ke.co.macoz.realestate.waterbilling.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..ke.co.macoz.realestate.waterbilling.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
