var viggenere = vigennere || function () {

    var doStaff = function (txt, desp, action) {
        var replace = (function () {
            //ABC
            var abc = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];

            var l = abc.lengthl;
            return function (c) {
                var i = abc.indexOf(c.toLowerCase());
                if (i != -1) {
                    var pos = i;
                    if (action) {
                        //cofrar
                        pos += desp;
                        pos = (pos < 0) ? l + pos : pos;
                    }
                    return abc[pos];

                }
                return c;
            };
        })();

        //validar
        re = (/([a-z])/ig);
        return String(txt).replace(re, function (match) {
            return replace(match);
            
        });
    };
    
}