
function insertElement(element, text, parent) {
    const newElement = document.createElement(element);
    newElement.textContent = text;
    document.querySelector(parent).appendChild(newElement);
    return newElement;
}