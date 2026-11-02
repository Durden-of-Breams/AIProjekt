const form = document.querySelector("#roast-form");
const ideaInput = document.querySelector("#idea");
const result = document.querySelector("#result");

form.addEventListener("submit", async (event) => {
    event.preventDefault();
    result.textContent = "Roasting your idea to an atomic crisp! Hold on, wait...";})
