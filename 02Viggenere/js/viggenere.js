var viggenere = viggenere || (function () {

    var doStaff = function (txt, desp, action) {
        var replace = (function () {
            //ABC
            var abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z'];
            var l = abc.length;

            return function (c) {
                var i = abc.indexOf(c.toLowerCase());
                if (i != -1) {
                    var pos = i;
                    if (action) {
                        //cifrar
                        pos += desp;
                        pos = (pos >= l) ? pos - l : pos;
                    } else {
                        //descifrar
                        pos -= desp;
                        pos = (pos < 0) ? l + pos : pos;
                    }
                    return abc[pos];
                }
                return c;
            };
        })();

        //validar
        var re = (/([a-z])/ig);

        return String(txt).replace(re, function (match) {
            return replace(match);
        });
    };
    return {
        //si vamos a ccodificar o decodificar osea decifrare o cifrar

        encode: function (txt, desp) {
            return doStaff(txt, desp, true);
        },
        decode: function (txt, desp) {
            return doStaff(txt, desp, false);
        }
    };
})();

function longitudCifrar() {
    camposVacios();
    var texto = document.getElementById("txt").value;
    var clave = document.getElementById("txtclave").value;

    if(clave.length > texto.length){\
        alert("la clave no puede ser bmas larga que el tesxto a cifrar");

    } else {
        codificar(texto, clave);
    }

}

function longitudDescifrar() {
    camposVacios();
    var texto = document.getElementById("txt").value;
    var clave = document.getElementById("txtclave").value;

    if(clave.length > texto.length){\
        alert("la clave no puede ser bmas larga que el tesxto a cifrar");

    } else {
        decodificar(texto, clave);
    }

}

function codificar(texto, clave) {
    var resultado = "";
    var indicaeClave = 0;
    var charArTexto = texto.split('');

    for (var i = 0; i < charArTexto.length; i++){

        var des = obtenerindiceClave(clave.charAt(indicaeClave));
        var charTexto = charArTexto[i]; 

        resultado += viggenere.encode(charTexto, (des >= 26) ? des % 26 : des);
        indicaeClave++;

        if (indicaeClave >= clave.length) {
            indicaeClave = 0;
        }
        document.getElementById("res").value = resultado;
    }
}

function decodificar(texto, clave) {
    var resultado = "";
    var indicaeClave = 0;
    var charArTexto = texto.split('');

    for (var i = 0; i < charArTexto.length; i++){

        var des = obtenerindiceClave(clave.charAt(indicaeClave));
        var charTexto = charArTexto[i]; 

        resultado += viggenere.decode(charTexto, (des >= 26) ? des % 26 : des);
        indicaeClave++;

        if (indicaeClave >= clave.length) {
            indicaeClave = 0;
        }
        document.getElementById("res").value = resultado;
    }
}

var obIndiceClave (reco() {
    //ABC
    var abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
        'l', 'm', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z'];
    return abc.indexOf(reco.toLowerCase());
}

function camposVacios() {
    var texto = document.getElementById("txt").value;
    var clave = document.getElementById("txtclave").value;

    if (texto == "") {
        alert("el texto no puede estar vabsio");
    } if (clave == "") {
        alert("la clave no puede estar vacia");

    }
    
}

function colocar() {
    var copiado = document.getElementById; ("res").value;
    document.getElementById("txt").value = copiado
}

function reiniciar() {
    document.getElementById; ("txt").value = "";
    document.getElementById; ("txtclave").value = "";
    document.getElementById; ("res").value = "";

}

