package cl.rhacs.detta.repositorios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cl.rhacs.detta.Utilidades;
import cl.rhacs.detta.modelos.Profesional;
import cl.rhacs.detta.modelos.database.Conexion;
import cl.rhacs.detta.repositorios.interfaces.IProfesionalesRepository;

public class ProfesionalesRepository implements IProfesionalesRepository {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /** Nombre de la tabla en la base de datos */
    private final String TABLA = "detta_profesionales";

    // Atributos
    // -----------------------------------------------------------------------------------------

    /** Objeto {@link Conexion} con los métodos de conexión a la base de datos */
    private Conexion conexion;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia del objeto {@link ProfesionalesRepository}
     * 
     * @param conexion objeto {@link Conexion} que contiene la conexión con la base
     *                 de datos
     */
    public ProfesionalesRepository(Conexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Extrae un {@link Profesional} del {@link ResultSet}
     * 
     * @param rs objeto {@link ResultSet} con la información a extraer
     * @return un objeto {@link Profesional}
     */
    private Profesional extraerProfesional(ResultSet rs) {
        // Crear objeto
        Profesional profesional = new Profesional();

        try {
            profesional.setId(rs.getInt("id"));
            profesional.setNombre(rs.getString("nombre"));
            profesional.setEmail(rs.getString("email"));
            profesional.setTelefono(rs.getString("telefono"));
            profesional.setEstadoContrato(rs.getString("estado_contrato"));
            profesional.setPassword(rs.getString("password"));
        } catch (SQLException e) {
            Utilidades.extraerError("ProfesionalesRepository", "extraerProfesional", e);
        }

        return profesional;
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
                String sql = "INSERT INTO " + TABLA + " (nombre, email, telefono, estado_contrato, "
                        + "password) VALUES (?, ?, ?, ?, ?)";

                // Convertir objeto
                Profesional profesional = (Profesional) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setString(1, profesional.getNombre());
                ps.setString(2, profesional.getEmail());
                ps.setString(3, profesional.getTelefono());
                ps.setString(4, profesional.getEstadoContrato());
                ps.setString(5, profesional.getPassword());

                // Ejecutar consulta
                registroAgregado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("ProfesionalesRepository", "agregarRegistro", e);
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
        List<Object> profesionales = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, email, telefono, estado_contrato, password FROM " + TABLA;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar lista
                    profesionales = new ArrayList<>();

                    // Extraer resultados
                    do {
                        Profesional profesional = extraerProfesional(rs);

                        // Agregar profesional al listado
                        profesionales.add(profesional);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("ProfesionalesRepository", "buscarTodos", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return profesionales;
    }

    @Override
    public Profesional buscarPorId(int id) {
        // Crear profesional
        Profesional profesional = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, email, telefono, estado_contrato, password FROM " + TABLA
                        + " WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Extraer profesional
                    profesional = extraerProfesional(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError("ProfesionalesRepository", "buscarPorId", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return profesional;
    }

    @Override
    public Profesional buscarPorEmail(String email) {
        // Crear profesional
        Profesional profesional = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, email, telefono, estado_contrato, password FROM " + TABLA
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
                    profesional = extraerProfesional(rs);
                }
            } catch (SQLException e) {
                Utilidades.extraerError("ProfesionalesRepository", "buscarPorEmail", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return profesional;
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
                String sql = "UPDATE " + TABLA + " SET nombre = ?, email = ?, telefono = ?, estado_contrato = ?"
                        + ", password = ? WHERE id = ?";

                // Convertir objeto
                Profesional profesional = (Profesional) objeto;

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Poblar consulta
                ps.setString(1, profesional.getNombre());
                ps.setString(2, profesional.getEmail());
                ps.setString(3, profesional.getTelefono());
                ps.setString(4, profesional.getEstadoContrato());
                ps.setString(5, profesional.getPassword());
                ps.setInt(6, profesional.getId());

                // Ejecutar consulta
                registroActualizado = ps.executeUpdate() > 0;
            } catch (SQLException e) {
                Utilidades.extraerError("ProfesionalesRepository", "actualizarRegistro", e);
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

        // Verificar conexión
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
                Utilidades.extraerError("ProfesionalesRepository", "eliminarRegistro", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return registroEliminado;
    }

}
