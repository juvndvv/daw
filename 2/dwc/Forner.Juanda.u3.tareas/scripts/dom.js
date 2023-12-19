export function insertElement(type, content, parent) {
    let element = document.createElement(type);
    element.innerHTML = content;

    document.querySelector(parent).appendChild(element);
    return element;
}

export function clearWindow() {
    document.querySelector(`main`).textContent = ``;
}