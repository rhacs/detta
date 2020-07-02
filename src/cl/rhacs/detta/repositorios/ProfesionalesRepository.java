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

    // Atributos
    // -----------------------------------------------------------------------------------------

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
            profesional.setDireccion(rs.getString("direccion"));
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
    public boolean agregarRegistro(Profesional profesional) {
        // Crear respuesta
        boolean registroAgregado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "INSERT INTO detta_profesionales (nombre, email, direccion, telefono, "
                        + "estado_contrato, password) VALUES (?, ?, ?, ?, ?, ?)";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setString(1, profesional.getNombre());
                ps.setString(2, profesional.getEmail());
                ps.setString(3, profesional.getDireccion());
                ps.setString(4, profesional.getTelefono());
                ps.setString(5, profesional.getEstadoContrato());
                ps.setString(6, profesional.getPassword());

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
    public List<Profesional> buscarTodos() {
        // Crear listado
        List<Profesional> profesionales = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, email, direccion, telefono, estado_contrato, "
                        + "password FROM detta_profesionales";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Obtener resultados
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar lista
                    profesionales = new ArrayList<>();

                    // Extraer profesionales
                    do {
                        Profesional profesional = extraerProfesional(rs);

                        // Agregar profesional a la lista
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
                String sql = "SELECT id, nombre, email, direccion, telefono, estado_contrato, "
                        + "password FROM detta_profesionales WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setInt(1, id);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hubo resultados
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
    public List<Profesional> buscarPor(String campo, String valor) {
        // Crear listado
        List<Profesional> profesionales = null;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "SELECT id, nombre, email, direccion, telefono, estado_contrato, "
                        + "password FROM detta_profesionales WHERE " + campo + " = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setString(1, valor);

                // Ejecutar consulta
                ResultSet rs = ps.executeQuery();

                // Verificar si hay resultados
                if (rs.next()) {
                    // Inicializar lista
                    profesionales = new ArrayList<>();

                    // Extraer profesionales
                    do {
                        Profesional profesional = extraerProfesional(rs);

                        // Agregar profesional a la lista
                        profesionales.add(profesional);
                    } while (rs.next());
                }
            } catch (SQLException e) {
                Utilidades.extraerError("ProfesionalesRepository", "buscarPor", e);
            } finally {
                // Desconectar
                conexion.desconectar();
            }
        }

        return profesionales;
    }

    @Override
    public boolean actualizarRegistro(Profesional profesional) {
        // Crear respuesta
        boolean registroActualizado = false;

        // Conectar
        Connection con = conexion.conectar();

        // Verificar conexión
        if (con != null) {
            try {
                // Definir consulta
                String sql = "UPDATE detta_profesionales SET nombre = ?, email = ?, direccion = ?, "
                        + "telefono = ?, estado_contrato = ? WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
                ps.setString(1, profesional.getNombre());
                ps.setString(2, profesional.getEmail());
                ps.setString(3, profesional.getDireccion());
                ps.setString(4, profesional.getTelefono());
                ps.setString(5, profesional.getEstadoContrato());
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
                String sql = "DELETE FROM detta_profesionales WHERE id = ?";

                // Preparar consulta
                PreparedStatement ps = con.prepareStatement(sql);

                // Llenar consulta
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
