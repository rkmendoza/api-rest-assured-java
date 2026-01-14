📄 README — api-rest-assured-java

📌 Overview

Este proyecto es un framework de automatización de pruebas para APIs REST, desarrollado en Java utilizando RestAssured y Cucumber, diseñado para ejecutarse tanto localmente como en pipelines CI/CD.

El objetivo principal es validar el comportamiento funcional de una API, detectar regresiones tempranas y proveer visibilidad clara del estado de calidad mediante reportes automáticos.

🎯 Objetivos del Framework

Validar endpoints REST con y sin autenticación

Cubrir flujos CRUD completos

Detectar regresiones funcionales

Ejecutar pruebas automáticamente en CI

Generar reportes claros para equipos técnicos

🧰 Stack Tecnológico

Java

Maven

RestAssured – automatización de pruebas API

Cucumber (BDD) – definición de escenarios en Gherkin

TestNG – ejecución de tests

Allure – reporting

GitHub Actions – CI/CD

🧪 Tipos de Pruebas

Functional API Testing

Positive scenarios

Negative scenarios

Authentication flows

CRUD operations

🏷️ Estrategia de Tags

El framework utiliza Cucumber tags para organizar la ejecución de pruebas:

@smoke
Pruebas críticas para validar que la API está operativa.

@regression
Suite completa de regresión funcional.

Tags funcionales:

@auth

@crud

@positive

@negative

Estos tags permiten ejecuciones selectivas en pipelines CI/CD.

📊 Reporting

Después de cada ejecución:

Se genera un reporte Allure

El reporte permite visualizar:

tests ejecutados

tests fallidos

duración

detalles por escenario

🔁 Ejecución

El framework está preparado para:

ejecución local

ejecución automática en CI/CD

futuras ejecuciones programadas (nightly)

🚀 Roadmap

Publicación automática de reportes Allure

Notificaciones (Telegram / Slack)

Ejecuciones nocturnas de regresión

Métricas de calidad

Validación de JSON Schema (contract testing)
