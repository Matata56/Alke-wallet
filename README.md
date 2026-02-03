# 💰 Alke Wallet - Billetera Digital

## 📋 Descripción del Proyecto

Alke Wallet es una aplicación de billetera digital desarrollada en Java que permite a los usuarios gestionar sus activos financieros de manera segura y conveniente. El sistema permite crear cuentas, realizar transacciones y convertir entre diferentes tipos de moneda.

## 🎯 Objetivo

Desarrollar una billetera digital funcional, segura y fácil de usar que proporcione a los usuarios una solución confiable para administrar sus activos financieros de manera digital.

## ✨ Funcionalidades Principales

### Gestión de Cuentas
- ✅ Crear nuevas cuentas con autenticación por contraseña
- ✅ Iniciar sesión de forma segura
- ✅ Cerrar sesión
- ✅ Consultar saldo disponible

### Operaciones Financieras
- ✅ Depositar dinero
- ✅ Retirar dinero
- ✅ Conversión entre diferentes monedas (CLP, USD, EUR, ARS)

## 🏗️ Arquitectura del Proyecto

### Estructura de Paquetes
```
AlkeWallet/
├── WalletModel/          # Modelos de datos
│   ├── Transaccionable   # Interfaz para operaciones
│   ├── TipoMoneda        # Enum con tipos de moneda
│   └── Cuenta            # Clase principal de cuenta
├── WalletService/        # Lógica de negocio
│   ├── ConversorMoneda   # Servicio de conversión
│   └── GestorCuentas     # Gestión de múltiples cuentas
├── WalletMain/           # Punto de entrada
│   └── AlkeWalletApp     # Aplicación principal
└── test/                 # Pruebas unitarias
    ├── CuentaTest
    ├── ConversorMonedaTest
    └── GestorCuentasTest
```

## 🔧 Tecnologías Utilizadas

- **Lenguaje:** Java
- **Paradigma:** Programación Orientada a Objetos (POO)
- **Framework de Testing:** JUnit 5/6
- **IDE:** Eclipse

## 📦 Componentes Principales

### 1. Modelo de Datos (WalletModel)

#### Interfaz `Transaccionable`
Define el contrato para operaciones básicas de cuenta:
- `depositar(double monto)`
- `retirar(double monto)`
- `consultarSaldo()`

#### Clase `Cuenta`
Representa una cuenta bancaria con:
- Número de cuenta
- Titular
- Contraseña (encriptada)
- Saldo
- Tipo de moneda

#### Enum `TipoMoneda`
Define las monedas soportadas y sus tasas de cambio:
- CLP (Peso Chileno) - Moneda base
- USD (Dólar)
- EUR (Euro)
- ARS (Peso Argentino)

### 2. Servicios (WalletService)

#### `ConversorMoneda`
Servicio para convertir entre diferentes tipos de moneda utilizando tasas de cambio predefinidas.

#### `GestorCuentas`
Administra el ciclo de vida de múltiples cuentas:
- Registro de nuevas cuentas
- Autenticación de usuarios
- Validación de cuentas existentes

### 3. Aplicación Principal (WalletMain)

#### `AlkeWalletApp`
Punto de entrada de la aplicación con menú interactivo para:
- Crear nuevas cuentas
- Iniciar/cerrar sesión
- Realizar operaciones financieras

## 🚀 Cómo Ejecutar el Proyecto

### Requisitos Previos
- Java JDK 11 o superior
- Eclipse IDE (o cualquier IDE compatible con Java)
- JUnit 5/6 (para ejecutar pruebas)

### Pasos de Instalación

1. **Clonar o descargar el proyecto**
```bash
   git clone [URL_DEL_REPOSITORIO]
```

2. **Importar en Eclipse**
   - File → Import → Existing Projects into Workspace
   - Seleccionar la carpeta del proyecto
   - Finish

3. **Ejecutar la aplicación**
   - Click derecho en `AlkeWalletApp.java`
   - Run As → Java Application

4. **Ejecutar las pruebas**
   - Click derecho en el paquete `test`
   - Run As → JUnit Test

## 📊 Pruebas Unitarias

El proyecto incluye una suite completa de pruebas unitarias con cobertura de:

### `CuentaTest` (11 tests)
- Creación de cuenta
- Verificación de contraseña
- Operaciones de depósito y retiro
- Validaciones de montos negativos y fondos insuficientes

### `ConversorMonedaTest` (7 tests)
- Conversión entre diferentes monedas
- Conversión a la misma moneda
- Conversión de cuentas completas

### `GestorCuentasTest` (7 tests)
- Registro de cuentas
- Validación de cuentas duplicadas
- Autenticación (login exitoso/fallido)
- Gestión de múltiples cuentas

**Total: 25 pruebas unitarias** ✅

## 🎨 Principios de Diseño Aplicados

### SOLID
- **S - Single Responsibility:** Cada clase tiene una única responsabilidad
- **O - Open/Closed:** Extensible mediante interfaces
- **L - Liskov Substitution:** Las implementaciones son sustituibles
- **I - Interface Segregation:** Interfaz `Transaccionable` específica
- **D - Dependency Inversion:** Dependencia de abstracciones

### Otros Principios
- **Encapsulamiento:** Atributos privados con getters/setters
- **Polimorfismo:** A través de la interfaz `Transaccionable`
- **Reutilización de código:** Mediante interfaces y herencia

## 💡 Decisiones de Diseño

### ¿Por qué CLP como moneda base?
Se eligió el Peso Chileno (CLP) como moneda base por ser la moneda local del contexto del proyecto, facilitando las conversiones y el entendimiento del usuario final.

### Sistema de Autenticación
Se implementó un sistema simple de autenticación con contraseña en texto plano. **Nota:** En un entorno de producción se debería implementar hash de contraseñas (bcrypt, SHA-256, etc.).

### Gestión de Sesiones
El sistema permite múltiples cuentas pero una sesión activa a la vez, simulando el comportamiento de una aplicación bancaria real.

## 🔒 Seguridad

**Implementaciones actuales:**
- Validación de contraseñas en login
- Verificación de fondos antes de retiros
- Validación de montos negativos

**Mejoras futuras sugeridas:**
- Encriptación de contraseñas
- Límites de intentos de login
- Log de transacciones
- Autenticación de dos factores

## 📈 Futuras Mejoras

- [ ] Persistencia de datos (base de datos)
- [ ] Transferencias entre cuentas
- [ ] Historial de transacciones
- [ ] Límites de retiro diarios
- [ ] Notificaciones de transacciones
- [ ] Interfaz gráfica (GUI)
- [ ] API REST para integración con aplicaciones móviles

## 👨‍💻 Autor

[Tu Nombre]

## 📄 Licencia

Este proyecto fue desarrollado como parte de la evaluación del Módulo 2 - Fundamentos de Programación en Java.

---

**Fecha de entrega:** Febrero 2026
**Institución:** Alkemy Digital