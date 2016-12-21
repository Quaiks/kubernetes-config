package com.github.k8.config

import com.typesafe.config.{Config, ConfigFactory}
import io.fabric8.kubernetes.client.DefaultKubernetesClient

import scala.util.{Success, Try}

object KubernetesConfig {

  private val localConfig = ConfigFactory.load()

  private val k8Config = {
    val k8Client = new DefaultKubernetesClient()
    val appName = localConfig.getString("application.name")
    Try(Option(k8Client.configMaps().withName(appName).get())) match {
      case Success(Some(configMap)) => ConfigFactory parseString configMap.getData.getOrDefault("application.json", "{}")
      case _ => ConfigFactory empty
    }
  }

  def load(): Config = k8Config withFallback localConfig

}
