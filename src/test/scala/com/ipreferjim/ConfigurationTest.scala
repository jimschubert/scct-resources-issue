package com.ipreferjim

import org.scalatest.FunSpec
import org.scalatest.matchers.ShouldMatchers

class ConfigurationTest extends FunSpec with ShouldMatchers {
   describe("Config") {
     it("should merge up") {
       Config.dbUser should equal("test")
       Config.dbPass should equal("test_pass")
       Config.dbDriver should equal("com.mysql.jdbc.Driver")
     }
   }
}
