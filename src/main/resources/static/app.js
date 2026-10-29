const URL_PAISES = "/api/v1/paises";
const SOLO_LETRAS_Y_ESPACIOS = /^[a-zA-Z ÑñáÁéÉíÍóÓúÚ]*$/;
const FORMATO_ID = /^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$/;
const formulario = document.getElementById("formularioPais");
const campoId = document.getElementById("idPais");
const campoNombre = document.getElementById("nombrePais");
const botonGuardar = document.getElementById("botonGuardar");
const botonCancelar = document.getElementById("botonCancelar");
const filtroNombre = document.getElementById("filtroNombre");
const botonBuscar = document.getElementById("botonBuscar");
const botonLimpiar = document.getElementById("botonLimpiar");
const listaPaises = document.getElementById("listaPaises");
const cajaMensaje = document.getElementById("mensaje");
const tituloFormulario = document.getElementById("tituloFormulario");
const tipoBusqueda = document.getElementById("tipoBusqueda");

function concatenar(...partes) {
    return partes.join("");
}
function validarObligatoriedad(dato){
    if(dato.trim()===""){
        mostrarMensaje("El nombre del pais es obligatorio,Asegurese que este entre 1 y 50 caracteres " , "error");
        return false;
    }
    return true;
}
function validarFormato(dato){
    if(!SOLO_LETRAS_Y_ESPACIOS.test(dato.trim())){
        mostrarMensaje("El nombre del pais debe tener unicamente letras de la a a la z ya sean mayusculas o minusculas " , "error");
        return false;
    }
    return true;
}
function validarId(id){
    if(!FORMATO_ID.test(id)){
        mostrarMensaje("El id no tiene un formato valido , recuerde copiarlo completo " , "error");
        return false;
    }
    return true;
}
function validarLongitud(dato){
    if(! (dato.trim().length >= 1 && dato.trim().length <= 50)){
        mostrarMensaje("El nombre del pais debe estar entre 1 y 50 caracteres", "error");
        return false;
    }
    return true;
}
function validarNombrePais(dato){
    if(!(validarObligatoriedad(dato) && validarFormato(dato)&& validarLongitud(dato))){
        return false;
    }
    return true;
}


async function llamarApi(url, metodo, cuerpo) {
    const opciones = { method: metodo, headers: { "Content-Type": "application/json" } };
    if (cuerpo) {
        opciones.body = JSON.stringify(cuerpo);
    }
    if (metodo !== "GET") {
        const csrf = await (await fetch("/api/v1/csrf")).json();
        opciones.headers[csrf.headerName] = csrf.token;

    }
    const respuesta = await fetch(url, opciones);
    const json = await respuesta.json();
    if (!respuesta.ok) {
        throw new Error(json.mensajes && json.mensajes.length > 0 ? json.mensajes[0] : "Se presentó un problema inesperado");
    }
    return json;
}

function mostrarMensaje(texto, tipo) {
    cajaMensaje.textContent = texto;
    cajaMensaje.className = concatenar("mensaje ", tipo);
    setTimeout(() => cajaMensaje.classList.add("oculto"), 4000);
}

async function consultarPaises(nombre) {
    try {
        let url = URL_PAISES;
        if (nombre && tipoBusqueda.value === "id") {
            if(!validarId(nombre.trim())){
                return;
            }
            url = concatenar(URL_PAISES, "/", nombre.trim());
        } else if (nombre && nombre.trim() !=="") {
            if(!validarFormato(nombre)){
                return;
            }
            url = concatenar(URL_PAISES, "?nombre=", encodeURIComponent(nombre));
        }

        const respuesta = await llamarApi(url, "GET");
        pintarTabla(respuesta.datos);
    } catch (error) {
        mostrarMensaje(error.message, "error");
    }
}
function pintarTabla(paises) {
    listaPaises.innerHTML = "";
    if (paises.length === 0) {
        listaPaises.innerHTML = '<p class="aviso">No se encontraron países</p>';
        return;
    }

    paises.forEach(pais => {
        const tarjeta = document.createElement("div");
        tarjeta.className = "tarjeta-pais";
        const nombre = document.createElement("h3");
        nombre.textContent = pais.nombre;
        const botonEditar = document.createElement("button");
        botonEditar.textContent = "Editar";
        botonEditar.className = "secundario";
        botonEditar.onclick = () => prepararEdicion(pais);
        const botonEliminar = document.createElement("button");
        botonEliminar.textContent = "Eliminar";
        botonEliminar.className = "peligro";
        botonEliminar.onclick = () => eliminarPais(pais);
        const id = document.createElement("small");
        id.textContent = concatenar("Id: ", pais.id);
        tarjeta.append(nombre, id, botonEditar, botonEliminar);
        listaPaises.appendChild(tarjeta);
    });
}

formulario.addEventListener("submit", async evento => {
    evento.preventDefault();
    if(!validarNombrePais(campoNombre.value)){
        return;
    }
    const datos = { nombre: campoNombre.value };
    try {
        let respuesta;
        const esModificacion = campoId.value;
        if (esModificacion){
            respuesta = await llamarApi(concatenar(URL_PAISES, "/", campoId.value), "PUT", datos);
        } else {
            respuesta = await llamarApi(URL_PAISES, "POST", datos);
        }
        mostrarMensaje(respuesta.mensajes[0], "exito");
        limpiarFormulario();
        if (esModificacion) {
            await consultarPaises(filtroNombre.value);
        }
    } catch (error) {
        mostrarMensaje(error.message, "error");
    }
});

function prepararEdicion(pais) {
    campoId.value = pais.id;
    campoNombre.value = pais.nombre;
    tituloFormulario.textContent = "Modificar país";
    botonGuardar.textContent = "Guardar cambios";
    botonCancelar.classList.remove("oculto");
    campoNombre.focus();
}


function limpiarFormulario() {
    campoId.value = "";
    campoNombre.value = "";
    tituloFormulario.textContent = "Registrar país";
    botonGuardar.textContent = "Registrar";
    botonCancelar.classList.add("oculto");
}

async function eliminarPais(pais) {
    if (!confirm(concatenar("¿Seguro que deseas eliminar ", pais.nombre, "?"))) {
        return;
    }
    try {
        const respuesta = await llamarApi(concatenar(URL_PAISES, "/", pais.id), "DELETE");
        mostrarMensaje(respuesta.mensajes[0], "exito");
        await consultarPaises(filtroNombre.value);
    } catch (error) {
        mostrarMensaje(error.message, "error");
    }
}

botonCancelar.addEventListener("click", limpiarFormulario);
botonBuscar.addEventListener("click", () => consultarPaises(filtroNombre.value));

botonLimpiar.addEventListener("click",
    async function () {
        filtroNombre.value = "";
        await consultarPaises();
    });

listaPaises.innerHTML = '<p class="aviso">Busca un país o presiona "Ver todos"</p>';