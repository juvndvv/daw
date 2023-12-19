`use strict`;

export function getDate() {
    let today = new Date(Date.now());
    let days = [`Domingo`, `Lunes`, `Martes`, `Miercoles`, `Jueves`, `Viernes`, `Sabado`];
    let months = [`enero`, `febrero`, `marzo`, `abril`, `mayo`, `junio`, `julio`, `agosto`, `septiembre`, `octubre`, `noviembre`, `diciembre`];

    return `${days[today.getDay()]}, ${today.getDate()} de ${months[today.getMonth()]} de ${today.getFullYear()}`;
}

export function actualTime() {
    let msFromEpoch = Date.now()

    let today = new Date(msFromEpoch);

    let year = today.getFullYear();
    let month = today.getMonth();
    let dayOfMonth = today.getDate();
    let dayOfWeek = today.getDay();
    let hour = today.getHours();
    let minutes = today.getMinutes();
    let miliseconds = today.getMilliseconds();

    return { year, month, dayOfMonth, dayOfWeek, hour, minutes, miliseconds, msFromEpoch };
}

export function askQuestion(question) {
    // Contamos el tiempo a las preguntas
    let start = Date.now();
    prompt(question);
    let end = Date.now();
    let ms = end - start;

    return ms;
}

export function msToTime(ms) {
    // Cambio a minutos, segundos y milisegundos
    let minutes = Math.floor(ms / 60000); // 1 minuto = 60000 milisegundos
    let seconds = Math.floor((ms % 60000) / 1000); // 1 segundo = 1000 milisegundos
    let milliseconds = ms % 1000;

    return { minutes, seconds, milliseconds };
}

export function birthdayDays(birthday) {
    let today = new Date();

    let years = today.getFullYear() - birthday.getFullYear();

    let months = birthday.getMonth() - today.getMonth();
    let days = birthday.getDate() - today.getDate();

    if (months > 0 || (months === 0 && days > 0)) { years--; }

    let whenItWas;
    if (months < 0) {
        whenItWas = -1;
        months = -months;
        
        if (days > 0) { months--; days = 30 - days }
        else if (days < 0) { days = -days }

    } else if (months > 0) {
        whenItWas = 1;
        if (days < 0) { months--; days = 30 - (-days) }

    } else { // el mismo mes
        if (days < 0) { whenItWas = -1; days = -days }
        else if (days > 0) { whenItWas = 1 }
        else { whenItWas = 0 }
    }

    return { whenItWas, years, months, days };
}

export function formatBirthdayData(data) {
    // Comprobamos si es hoy
    if (data.whenItWas === 0) {
        return data.years === 0 ? `Acabas de nacer?` : `Feliz ${data.years} cumpleaños`;
    }
    
    let str = "";

    // Si es pasado o futuro
    switch (data.whenItWas) {
        case -1:
            str += data.years === 0 ? `Naciste hace` : `Fue hace`;
            break;

        case 1:
            str += `Es dentro de`
            break;
    }

    // Añadimos el tiempo que le queda
    switch (true) {
        case data.months > 0:
            str += ` ${data.months} ${data.months === 1 ? "mes" : "meses"}`;
            if (data.days > 0) { str += ` y ${data.days} ${data.days === 1 ? " dia" : " dias"}` }
            break;
        case data.days > 0:
            str += ` ${data.days} ${data.days === 1 ? "dia" : "dias"}`;
            break;
    }

    str += `. Tienes ${data.years} ${data.years === 1 ? " año" : " años"}`;

    return str;
}

export function isNotValidDate(dateStr) {
    if (dateStr === ``) {
        return true;
    }

    // Comprueba que la fecha sea valida
    const date = dateStr.split(`-`);
    const year = parseInt(date[0]);
    const month = parseInt(date[1]);
    const day = parseInt(date[2]);

    // Comprueba que los dias y los meses tengan sentido
    if (day < 1 || day > 31 || month < 1 || month > 12) {
        return true;
    }

    // Comprueba que la fecha no sea futura
    const today = new Date(Date.now());
    const birthday = new Date(year, month - 1, day);
    if (birthday > today) {
        return true;
    }
}