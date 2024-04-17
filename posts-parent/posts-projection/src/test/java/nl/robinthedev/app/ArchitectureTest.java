package nl.robinthedev.app;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.methods;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.axonframework.eventhandling.EventHandler;

@AnalyzeClasses(packages = "nl.robinthedev.app.post.projection")
public class ArchitectureTest {

  @ArchTest
  private static final ArchRule eventHandlerRules =
      methods()
          .that()
          .areDeclaredInClassesThat()
          .haveSimpleNameEndingWith("Handler")
          .and()
          .areNotPrivate()
          .should()
          .beAnnotatedWith(EventHandler.class)
          .andShould()
          .haveName("handle");
}
