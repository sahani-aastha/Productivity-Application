function addRecommendation() {
    // Get the message of the new recommendation
    let recommendation = document.getElementById("new_recommendation");

    // If the user has left a recommendation, display a pop-up
    if (recommendation.value != null && recommendation.value.trim() != "") {
        console.log("New recommendation added");
        // Call showPopup here to display the popup
        showPopup(true);

        // Create a new 'recommendation' element and set its value to the user's message
        var element = document.createElement("div");
        element.setAttribute("class", "recommendation");

        // Use textContent to avoid HTML injection vulnerabilities
        element.textContent = `"${recommendation.value}"`;

        // Add this element to the end of the list of recommendations
        document.getElementById("all_recommendations").appendChild(element);

        // Reset the value of the textarea
        recommendation.value = "";
    }
}

function showPopup(bool) {
    if (bool) {
        document.getElementById('popup').style.visibility = 'visible';
    } else {
        document.getElementById('popup').style.visibility = 'hidden';
    }
}
