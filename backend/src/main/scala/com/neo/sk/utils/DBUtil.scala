package com.neo.sk.utils

import com.zaxxer.hikari.HikariDataSource
import org.slf4j.LoggerFactory
import slick.driver.PostgresDriver.api._
import slick.jdbc.PostgresProfile.api._
import com.neo.sk.break.common.AppSettings._

/**
 * User: Taoz
 * Date: 2/9/2015
 * Time: 4:33 PM
 */
object DBUtil {
  val log = LoggerFactory.getLogger(this.getClass)
  private val dataSource = createDataSource()

  private def createDataSource() = {

    val dataSource = new org.postgresql.ds.PGSimpleDataSource()

    //val dataSource = new MysqlDataSource()

    log.info(s"connect to db: $slickUrl")
    dataSource.setUrl(slickUrl)
    dataSource.setUser(slickUser)
    dataSource.setPassword(slickPassword)

    val hikariDS = new HikariDataSource()
    hikariDS.setDataSource(dataSource)
    hikariDS.setMaximumPoolSize(slickMaximumPoolSize)
    hikariDS.setConnectionTimeout(slickConnectTimeout)
    hikariDS.setIdleTimeout(slickIdleTimeout)
    hikariDS.setMaxLifetime(slickMaxLifetime)
    hikariDS.setAutoCommit(true)
    hikariDS
  }

  val maxC:Option[Int] =  Some(10)
  val db = Database.forDataSource(dataSource,maxC)




}