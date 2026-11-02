const form = document.querySelector("#roast-form");
const ideaInput = document.querySelector("#idea");
const result = document.querySelector("#result");
const button = form.querySelector("button");

form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const idea = ideaInput.value.trim();
    if (!idea) return;

    button.disabled = true;
    result.textContent = "Roasting your idea to an atomic crisp! Hold on, wait...";

    try {
        const res = await fetch("/api/roast/demo", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({roastBody: idea})
        });

        if (!res.ok) {
            result.textContent = `The roaster burned out (HTTP ${res.status}). Try again.`;
            return;
        }

        const data = await res.json();
        result.textContent = `${data.roastBody}` + `\n \nYour idea is: ${data.roastScore.toFixed(1)}/10 garbage`;
    } catch (err) {
        result.textContent = "Couldn't reach the server. Is it running?";
    }
    finally {
        button.disabled = false;
    }
});