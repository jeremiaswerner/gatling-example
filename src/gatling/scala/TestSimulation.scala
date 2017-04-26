import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TestSimulation extends Simulation {
    val httpProtocol = http
        .baseURL(s"https://openwhisk.ng.bluemix.net")

    val michael = scenario("Test Scenario").forever {
        exec(http("Info GET").get("/api/v1")).pause(5.seconds)
    }

    setUp(
        michael.inject(atOnceUsers(1))
    ).maxDuration(2.minutes).protocols(httpProtocol)
}
