const express = require('express')
const mysql = require('mysql')
const bodyParser = require('body-parser')

const app = express()

app.use(function(req, res, next) {
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', '*');
    next()
})

app.use(bodyParser.json())

const PUERTO = 3000

const conexion = mysql.createConnection(
    {
        host:'localhost',
        database:'examen',
        user:'root',
        password:'root'
    }
)

app.listen(PUERTO, '0.0.0.0', () => {
    console.log(`Servidor corriendo en el puerto ${PUERTO}`);
});

conexion.connect(error => {
    if(error) throw error
    console.log('Conexión exitosa a la base de datos');
})

let obj = {}

app.get('/', (req, res) => {
    res.send('API')
})

app.get('/entradas', (req, res) => {
    const query = `SELECT * FROM entradas;`
    conexion.query(query, (error, resultado) => {
        if(error) return console.error(error.message)

        if(resultado.length > 0) {
            obj.codigo = "200"
            obj.mensaje = "Lista entradas"
            obj.data = resultado
            res.json(obj)
        } else {
            obj.codigo = "400"
            obj.mensaje = "No hay registros"
            obj.data = []
            res.json(obj)
        }
    })
})

app.get('/entradas/:id', (req, res) => {
    const { id } = req.params

    const query = `SELECT * FROM entradas WHERE idEntrada=${id};`
    conexion.query(query, (error, resultado) => {
        if(error) return console.error(error.message)

        if(resultado.length > 0) {
            obj.codigo = "200"
            obj.mensaje = "entrada"
            obj.data = resultado
            res.json(obj)
        } else {
            obj.codigo = "400"
            obj.mensaje = "No hay registros"
            obj.data = []
            res.json(obj)
        }
    })
})


app.get('/entrada', (req, res) => {
    const { q } = req.query;

    if (!q) {
        res.json({
            codigo: "400",
            mensaje: "La consulta de búsqueda está vacía",
            data: []
        });
        return;
    }

    const query = `
    SELECT * FROM entradas
    WHERE titulo LIKE '%${q}%' OR contenido LIKE '%${q}%' OR autor LIKE '%${q}%';
`;

    const searchParam = `%${q}%`;


    conexion.query(query, [searchParam, searchParam, searchParam], (error, resultado) => {
        if (error) {
            console.error("Error en la consulta de búsqueda:", error.message);
            res.json({
                codigo: "500",
                mensaje: "Error en la consulta de búsqueda",
                data: []
            });
            return;
        }


        if (resultado.length > 0) {
            res.json({
                codigo: "200",
                mensaje: `Resultados de búsqueda para: ${q}`,
                data: resultado
            });
        } else {
            res.json({
                codigo: "404",
                mensaje: `No se encontraron resultados para: ${q}`,
                data: []
            });
        }
    });
});

app.post('/entradas/agregar', (req, res) => {
    const entrada = {
        titulo: req.body.titulo,
        autor: req.body.autor,
        fecha: req.body.fecha,
        contenido: req.body.contenido
    }

    const query = `INSERT INTO entradas SET ?`
    conexion.query(query, entrada, (error) => {
        if(error) return console.error(error.message)

        obj.codigo = "200"
        obj.mensaje = "Se insertó correctamente la entrada"
        obj.data = []
        res.json(obj)
    })
})

app.put('/entradas/actualizar/:id', (req, res) => {
    const { id } = req.params
    const { titulo, autor, fecha, contenido } = req.body

    const query = `UPDATE entradas 
                    SET titulo='${titulo}',
                        autor='${autor}' 
                        fecha='${fecha}' 
                        contenido='${contenido}' 
                    WHERE idEntrada='${id}';`
    conexion.query(query, (error) => {
        if(error) return console.error(error.message)

        obj.codigo = "200"
        obj.mensaje = "Se actualizó correctamente la entrada"
        obj.data = []
        res.json(obj)
    })
})

app.delete('/entradas/borrar/:id', (req, res) => {
    const { id } = req.params

    const query = `DELETE FROM entradas WHERE idEntrada=${id};`
    conexion.query(query, (error) => {
        if(error) console.error(error.message)

        obj.codigo = "200"
        obj.mensaje = "Se eliminó correctamente la entrada"
        obj.data = []
        res.json(obj)
    })
})