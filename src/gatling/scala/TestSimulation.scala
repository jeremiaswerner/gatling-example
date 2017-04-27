import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class TestSimulation extends Simulation {
    val host = sys.env("OPENWHISK_HOST")
    val requestsPerUser = sys.env("REQUESTS_PER_USER").toInt
    val users = sys.env("USERS").toInt

    val httpProtocol = http.baseURL(host)

    val test = scenario("Test Scenario").repeat(requestsPerUser) {
        exec(http("Info GET").get("/api/v1"))
    }

    setUp(
        test.inject(atOnceUsers(users))
    ).protocols(httpProtocol)
}
