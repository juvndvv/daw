
document.querySelector(`body`).insertBefore(
    document.createElement(`nav`),
    document.querySelector(`main`)
);

const argumentsBtn = insertElement(`button`, `Arguments`, `nav`);
argumentsBtn.addEventListener(`click`, () => {
    function concatenar() {
        const args = Array.from(arguments);
        return args.join(``);
    }

    console.log(concatenar(`hola`, ` `, `mundo`));
});