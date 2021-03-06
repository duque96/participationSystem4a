package participationSystem.cucumber.steps;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import participationSystem.util.SeleniumUtils;

public class OrdenarPorVotosSteps extends SuperSteps{
	
	@Given("^Inicio sesion en la aplicacion3$")
	public void inicio_sesion_en_la_aplicacion() throws Throwable {
		driver.get(baseUrl);
		driver.findElement(By.xpath("//*[@id=\"inputEmail\"]")).sendKeys("pelayo@gmail.com");
		driver.findElement(By.id("inputPassword")).sendKeys("temporal");
		driver.findElement(By.name("botonlogin")).click();

		SeleniumUtils.esperaCargaPaginaxpath(driver, "/html/body/div/div/div[2]/div[1]/h2", 4);
	}
	@Given("^Existen al menos dos sugerencia para ordenar3$")
	public void existen_al_menos_dos_sugerencia_para_ordenar() throws Throwable {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"sugerencias\"]/tbody/tr[1]/td[1]")).getText(), "Marquesina Llamaquique");
		assertEquals(driver.findElement(By.xpath("//*[@id=\"sugerencias\"]/tbody/tr[2]/td[2]")).getText(), "Plantacion de nuevos arboles en el Campo San Francisco");
	}
	
	@When("^Le doy a ordenar por Votos$")
	public void le_doy_a_ordenar_por_Votos() throws Throwable {
		driver.findElement(By.xpath("//*[@id=\"sugerencias\"]/thead/tr/th[3]")).click();
		
	}

	@Then("^Se ordena por votos$")
	public void se_ordena_por_votos() throws Throwable {
		assertEquals(driver.findElement(By.xpath("//*[@id=\"sugerencias\"]/tbody/tr[1]/td[1]")).getText(),
				"Marquesina Llamaquique");
		assertEquals(driver.findElement(By.xpath("//*[@id=\"sugerencias\"]/tbody/tr[2]/td[1]")).getText(),
				"Nuevos Ã¡rboles");
	}
}
