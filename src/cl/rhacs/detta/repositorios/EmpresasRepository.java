package cl.rhacs.detta.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.rhacs.detta.modelos.Empresa;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.interfaces.IEmpresasRepository;

public class EmpresasRepository implements IEmpresasRepository {

    // Atributos
    // -----------------------------------------------------------------------------------------

    private Conexion conexion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link EmpresasRepository}
     * 
     * @param conexion objeto {@link Conexion} con la conexión a la base de datos
     */
    public EmpresasRepository(Conexion conexion) {
        this.conexion = conexion;
    }

    // Métodos
    // -----------------------------------------------------------------------------------------

    /**
     * Extrae la información de la {@link Exception} y la muestra por consola
     * 
     * @param funcion nombre de la funcion donde ocurrió el error
     * @param e       objeto {@link Exception} con la información del error
     */
    private void extraerError(String funcion, Exception e) {
        // Mostrar cabecera
        System.err.println("EmpresasRepository#" + funcion + "()");

        if (e instanceof SQLException) {
            // Obtener código de estado
            String codigo = ((SQLException) e).getSQLState();

            // Mostrar código
            System.err.println(" [!] Código de Estado SQL: " + codigo);
        }

        // Obtener mensaje
        String mensaje = e.getLocalizedMessage() == null ? e.getMessage() : e.getLocalizedMessage();

        // Mostrar mensaje
        System.err.println(" [!] " + mensaje);
    }

    /**
     * Extrae una {@link Empresa} de un {@link ResultSet}
     * 
     * @param rs objeto {@link ResultSet} con la información a extraer
     * @return un objeto {@link Empresa}
     */
    private Empresa extraerEmpresa(ResultSet rs) {
        // Crear empresa
        Empresa empresa = new Empresa();

        try {
            // Llenar empresa
            empresa.setId(rs.getInt("id"));
            empresa.setNombre(rs.getString("nombre"));
            empresa.setRut(rs.getString("rut"));
            empresa.setDireccion(rs.getString("direccion"));
            empresa.setTelefono(rs.getString("telefono"));
            empresa.setGiro(rs.getString("giro"));
            empresa.setTrabajadores(rs.getInt("trabajadores"));
            empresa.setTipo(rs.getString("tipo"));
            empresa.setRegistro(rs.getTimestamp("registro").toLocalDateTime());
            empresa.setActualizacion(rs.getTimestamp("actualizacion").toLocalDateTime());
        } catch (SQLException e) {
            extraerError("extraerEmpresa", e);
        }

        return empresa;
    }

    // CRUD
    // -----------------------------------------------------------------------------------------

    @Override
    public boolean agregarRegistro(Object objeto, int padreId) {
        // Crear respuesta
        boolean registroAgregado = false;

        // Realizar conexión
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "INSERT INTO empresas (nombre, rut, direccion, telefono, giro, "
                        + "trabajadores, tipo, registro, actualizacion, profesional_id) VALUES (?, "
                        + "?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";

                // Convertir objeto
                Empresa empresa = (Empresa) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Insertar datos en la consulta
                ps.setString(1, empresa.getNombre());
                ps.setString(2, empresa.getRut());
                ps.setString(3, empresa.getDireccion());
                ps.setString(4, empresa.getTelefono());
                ps.setString(5, empresa.getGiro());
                ps.setInt(6, empresa.getTrabajadores());
                ps.setString(7, empresa.getTipo());

                // Insertar registro en la base de datos
                registroAgregado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                extraerError("agregarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroAgregado;
    }

    @Override
    public List<Object> buscarTodos() {
        // Crear respuesta
        List<Object> empresas = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar si hubo conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, firo, trabajadores, "
                        + "tipo, registro, actualizacion FROM empresas";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hubo al menos un resultado
                if (rs.next()) {
                    // Inicializar el listado
                    empresas = new ArrayList<>();

                    // Extraer todos los resultados
                    do {
                        Empresa empresa = extraerEmpresa(rs);

                        // Ingresar empresa al listado
                        empresas.add(empresa);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                extraerError("buscarTodos", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return empresas;
    }

    @Override
    public Object buscarPorId(int id) {
        // Crear objeto
        Empresa empresa = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar si hay conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, giro, trabajadores, "
                        + "tipo, registro, actualizacion FROM empresas WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Obtener resultados
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Extraer empresa
                    empresa = extraerEmpresa(rs);
                }
            } catch (SQLException e) {
                extraerError("buscarPorId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return empresa;
    }

    @Override
    public boolean actualizarRegistro(Object objeto) {
        // Crear respuesta
        boolean registroActualizado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar si hay conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "UPDATE empresas SET nombre = ?, rut = ?, direccion = ?, telefono = ?, "
                        + "giro = ?, trabajadores = ?, tipo = ?, actualizacion = CURRENT_TIMESTAMP WHERE id = ?";

                // Convertir objeto
                Empresa empresa = (Empresa) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar valores de la consulta
                ps.setString(1, empresa.getNombre());
                ps.setString(2, empresa.getRut());
                ps.setString(3, empresa.getDireccion());
                ps.setString(4, empresa.getTelefono());
                ps.setString(5, empresa.getGiro());
                ps.setInt(6, empresa.getTrabajadores());
                ps.setString(7, empresa.getTipo());

                // Ejecutar consulta
                registroActualizado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                extraerError("actualizarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroActualizado;
    }

    @Override
    public boolean eliminarRegistro(int id) {
        // Crear respuesta
        boolean registroEliminado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar si hay conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "DELETE FROM empresas WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Rellenar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                registroEliminado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                extraerError("eliminarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroEliminado;
    }

    @Override
    public Empresa buscarPorRut(String rut) {
        // Crear objeto
        Empresa empresa = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar si hay conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, giro, trabajadores, "
                        + "tipo, registro, actualizacion FROM empresas WHERE rut = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setString(1, rut);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    empresa = extraerEmpresa(rs);
                }
            } catch (SQLException e) {
                extraerError("buscarPorRut", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return empresa;
    }

    @Override
    public List<Empresa> buscarPorGiro(String giro) {
        // Crear listado
        List<Empresa> empresas = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar si hay conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, giro, trabajadores, "
                        + "tipo, registro, actualizacion FROM empresas WHERE giro LIKE ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setString(1, '%' + giro + '%');

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar listado
                    empresas = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Empresa empresa = extraerEmpresa(rs);

                        // Agregar empresa al listado
                        empresas.add(empresa);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                extraerError("buscarPorGiro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return empresas;
    }

}
