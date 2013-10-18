package com.ipreferjim

import com.typesafe.config.{Config => TypesafeConfig, ConfigFactory}

object ScctResourcesIssue extends App {
  println("Hello, SCCT Resources Issue")
}

object Config {
  private[this] val config = ConfigFactory.load()

  val dbUser: String = config.getString("db.default.user")
  val dbPass: String = config.getString("db.default.password")
  val dbDriver: String = config.getString("db.default.driver")
}
