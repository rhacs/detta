package cl.rhacs.detta.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.rhacs.detta.Utilidades;
import cl.rhacs.detta.modelos.Empresa;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.interfaces.IEmpresasRepository;

public class EmpresasRepository implements IEmpresasRepository {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Nombre de la tabla en la base de datos */
    private final String TABLA = "detta_empresas";

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link Conexion} con los métodos de conexión a la base de datos
     */
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
            empresa.setEmail(rs.getString("email"));
            empresa.setGiro(rs.getString("giro"));
            empresa.setTrabajadores(rs.getInt("trabajadores"));
            empresa.setTipo(rs.getString("tipo"));
            empresa.setPassword(rs.getString("password"));
            empresa.setFechaRegistro(rs.getTimestamp("fecha_registro").toLocalDateTime());
            empresa.setFechaActualizacion(rs.getTimestamp("fecha_actualizacion").toLocalDateTime());
            empresa.setProfesionalId(rs.getInt("profesional_id"));
        } catch (SQLException e) {
            Utilidades.extraerError("EmpresasRepository", "extraerEmpresa", e);
        }

        return empresa;
    }

    // CRUD
    // -----------------------------------------------------------------------------------------

    @Override
    public boolean agregarRegistro(Object objeto) {
        // Crear respuesta
        boolean registroAgregado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "INSERT INTO " + TABLA + " (nombre, rut, direccion, telefono, email, "
                        + "giro, trabajadores, tipo, password, profesional_id) VALUES (?, ?, ?, ?, "
                        + "?, ?, ?, ?, ?, ?)";

                // Convertir objeto
                Empresa empresa = (Empresa) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setString(1, empresa.getNombre());
                ps.setString(2, empresa.getRut());
                ps.setString(3, empresa.getDireccion());
                ps.setString(4, empresa.getTelefono());
                ps.setString(5, empresa.getEmail());
                ps.setString(6, empresa.getGiro());
                ps.setInt(7, empresa.getTrabajadores());
                ps.setString(8, empresa.getTipo());
                ps.setString(9, empresa.getPassword());
                ps.setInt(10, empresa.getProfesionalId());
                
                // Ejecutar consulta
                registroAgregado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("EmpresasRepository", "agregarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroAgregado;
    }

    @Override
    public List<Object> buscarTodos() {
        // Crear listado
        List<Object> empresas = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, email, giro, trabajadores, "
                        + "tipo, password, fecha_registro, fecha_actualizacion, profesional_id FROM " + TABLA;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

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
                Utilidades.extraerError("EmpresasRepository", "buscarTodos", e);
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

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, email, giro, trabajadores, "
                        + "tipo, password, fecha_registro, fecha_actualizacion, profesional_id FROM " + TABLA
                        + " WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Extraer resultado
                    empresa = extraerEmpresa(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError("EmpresasRepository", "buscarPorId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return empresa;
    }

    @Override
    public Empresa buscarPorEmail(String email) {
        // Crear objeto
        Empresa empresa = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, email, giro, trabajadores, "
                        + "tipo, password, fecha_registro, fecha_actualizacion, profesional_id FROM " + TABLA
                        + " WHERE email = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setString(1, email);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Extraer resultado
                    empresa = extraerEmpresa(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError("EmpresasRepository", "buscarPorEmail", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return empresa;
    }

    @Override
    public Empresa buscarPorRut(String rut) {
        // Crear objeto
        Empresa empresa = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, email, giro, trabajadores, "
                        + "tipo, password, fecha_registro, fecha_actualizacion, profesional_id FROM " + TABLA
                        + " WHERE rut = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setString(1, rut);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Extraer resultado
                    empresa = extraerEmpresa(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError("EmpresasRepository", "buscarPorRut", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return empresa;
    }

    @Override
    public List<Empresa> buscarPorProfesionalId(int profesionalId) {
        // Crear listado
        List<Empresa> empresas = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, rut, direccion, telefono, email, giro, trabajadores, "
                        + "tipo, password, fecha_registro, fecha_actualizacion, profesional_id FROM " + TABLA
                        + " WHERE profesional_id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, profesionalId);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar listado
                    empresas = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Empresa empresa = extraerEmpresa(rs);

                        // Agregar al listado
                        empresas.add(empresa);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("EmpresasRepository", "buscarPorProfesionalId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return empresas;
    }

    @Override
    public boolean actualizarRegistro(Object objeto) {
        // Crear respuesta
        boolean registroActualizado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "UPDATE " + TABLA + " SET nombre = ?, rut = ?, direccion = ?, telefono = ?, "
                        + "email = ?, giro = ?, trabajadores = ?, tipo = ?, password = ?, fecha_actualizacion = "
                        + "CURRENT_TIMESTAMP, profesional_id = ? WHERE id = ?";

                // Convertir objeto
                Empresa empresa = (Empresa) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setString(1, empresa.getNombre());
                ps.setString(2, empresa.getRut());
                ps.setString(3, empresa.getDireccion());
                ps.setString(4, empresa.getTelefono());
                ps.setString(5, empresa.getEmail());
                ps.setString(6, empresa.getGiro());
                ps.setInt(7, empresa.getTrabajadores());
                ps.setString(8, empresa.getTipo());
                ps.setString(9, empresa.getPassword());
                ps.setInt(10, empresa.getProfesionalId());
                ps.setInt(11, empresa.getId());

                // Ejecutar consulta
                registroActualizado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("EmpresasRepository", "actualizarRegistro", e);
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

        // Verificar conexion
        if (con != null) {
            try {
                // Definir consulta
                String sql = "DELETE FROM " + TABLA + " WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                registroEliminado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("EmpresasRepository", "eliminarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroEliminado;
    }

}
