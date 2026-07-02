# 🏦 Banco Mundial

Sistema bancario con interfaz gráfica Swing. Proyecto Final de Programación II.

## Requisitos

| Herramienta  | Versión | Notas |
|--------------|---------|-------|
| Docker       | 24+     | Para BD y build |
| JDK          | 21+     | Solo para ejecutar (el build puede ir en Docker) |
| Maven        | 3.9+    | Opcional si usás build de Docker |

## Stack

- **Lenguaje**: Java 21
- **GUI**: Swing (JDialog, GridBagLayout)
- **BD**: MySQL 8.0
- **Build**: Maven + shade-plugin (fat jar)
- **Infra**: Docker Compose
- **Auth**: BCrypt (jbcrypt 0.4)
- **Conector**: mysql-connector-j 8.0.33

## Quick Start

```bash
# 1. Compilar (en Docker)
./build.sh

# 2. Levantar BD + ejecutar app
./run.sh
```

### Sin Docker (build local)

```bash
mvn package -DskipTests
docker compose up -d db
java -jar target/banco-mundial-1.0.0.jar
```

### Solo build en Docker, app nativa

```bash
docker compose run --rm build
docker compose up -d db
java -jar target/banco-mundial-1.0.0.jar
```

## Estructura del proyecto

```
├── docker-compose.yml          # MySQL + build service
├── sql/init.sql                # Schema + seed data
├── pom.xml                     # Maven + shade-plugin
├── build.sh                    # Compila con Docker
├── run.sh                      # Levanta BD + ejecuta app
├── src/
│   └── main/
│       ├── java/com/banco/
│       │   ├── Aplicacion.java           # Entry point
│       │   ├── controlador/
│       │   │   ├── ConexionBD.java       # Singleton conexión MySQL
│       │   │   ├── ControladorCajero.java
│       │   │   ├── ControladorCambios.java
│       │   │   ├── ControladorLogin.java
│       │   │   ├── ControladorPerfil.java
│       │   │   ├── ControladorRegister.java
│       │   │   └── ControladorReporte.java
│       │   ├── modelo/
│       │   │   └── Contrasena.java       # Validación de contraseñas
│       │   ├── util/
│       │   │   └── AppTheme.java         # Colores y constantes UI
│       │   └── vista/
│       │       ├── Cajero.java           # Panel principal del cajero
│       │       ├── CalculosPlanes.java   # Calculadora de interés
│       │       ├── CambioContrasena.java
│       │       ├── CambioUsuario.java
│       │       ├── ImagePanel.java       # Panel con imagen escalada
│       │       ├── Login.java
│       │       ├── Menu.java             # Menú principal
│       │       ├── MenuPrincipal.java
│       │       ├── Pagos.java            # Pago de servicios
│       │       ├── Perfil.java
│       │       ├── Planes.java           # Selección de plan de ahorro
│       │       ├── Register.java         # Registro de nuevos clientes
│       │       ├── Reporte.java          # Historial de transacciones
│       │       └── Transferencia.java    # Transferencias entre cuentas
│       └── resources/                    # Imágenes (logo, iconos)
└── BaseDatos/
    ├── bancoMundial_create(1).sql        # Schema original (obsoleto)
    └── Dump20240617/                     # Dump de referencia
```

## Funcionalidades

- **Registro de clientes** con validación de contraseña segura
- **Inicio de sesión** con BCrypt
- **Depósitos y retiros** simulados vía transacciones
- **Transferencias** entre cuentas
- **Pago de servicios** (Luz, Agua, Gas, Internet)
- **Reportes** de movimientos con saldo calculado
- **Planes de ahorro** con cálculo de interés compuesto
- **Perfil** con datos personales
- **Cambio de usuario y contraseña**

## Arquitectura de BD

El schema se inicializa automáticamente con Docker. Tablas:

- `usuario` — credenciales (con hash bcrypt)
- `personas` — datos personales unificados (id_cliente, nombres, cédula, teléfono, fecha_nacimiento)
- `cuentas` — tipos de cuenta con tasa de interés (junior, clásica, senior, premium)
- `cuentas_cliente` — vinculación cliente-cuenta
- `transacciones` — movimientos (depósito/retiro con monto y fecha)

> **Nota**: El schema original separaba `personas` y `cliente`. El código actual unifica todo en `personas`.
