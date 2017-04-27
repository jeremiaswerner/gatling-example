import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TestSimulation extends Simulation {
    val httpProtocol = http.baseURL(s"https://openwhisk.ng.bluemix.net")

    val test = scenario("Test Scenario").repeat(1000) {
        exec(http("Info GET").get("/api/v1"))
    }

    setUp(
        test.inject(atOnceUsers(1))
    ).protocols(httpProtocol)
}
