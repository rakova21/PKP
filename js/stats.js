google.charts.load("current", {packages: ["corechart"]});
google.charts.setOnLoadCallback(topQuantity);
google.charts.setOnLoadCallback(drawEducation);
google.charts.setOnLoadCallback(topExp);
google.charts.setOnLoadCallback(drawMedExp);
google.charts.setOnLoadCallback(drawCertification);

function drawCertification() {
    let res = [['Балл', 'Количество', {role: 'style'}]];

    res.push(["Ниже 5", minCertification, 'red']);
    res.push(["5-7", medCertification, 'yellow']);
    res.push(["Выше 7", maxCertification, 'green']);

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Результаты аттестации',
        hAxis: {title: 'Балл'},
        vAxis: {title: 'Количество'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };
    var chart = new google.visualization.ColumnChart(document.getElementById('drawCertification'));
    chart.draw(data, options);
}

function topQuantity() {
    let res = [['ФИО', 'Баллов']];

    for (let i = 0; i < topQuantityName.length; i++) {
        res.push([topQuantityName[i], topQuantityNumber[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Топ-5 сотрудников',
        hAxis: {title: 'ФИО'},
        vAxis: {title: 'Баллов'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };
    var chart = new google.visualization.ColumnChart(document.getElementById('topQuantity'));
    chart.draw(data, options);
}

function topExp() {
    let res = [['ФИО', 'Стаж']];

    for (let i = 0; i < expString.length; i++) {
        res.push([expString[i], expInt[i]]);
    }

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Топ-5 сотрудников по стажу',
        hAxis: {title: 'ФИО'},
        vAxis: {title: 'Стаж'},
        bar: {groupWidth: "80%"},
        legend: {position: "none"}
    };
    var chart = new google.visualization.ColumnChart(document.getElementById('topExp'));
    chart.draw(data, options);
}

function drawEducation() {
    let res = [['Образование', 'Количество']];

    res.push(["Среднее", mid]);
    res.push(["Высшее", high]);

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Образование',
        pieHole: 0.2,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawEducation'));
    chart.draw(data, options);
}

function drawMedExp() {
    let res = [['Стаж', 'Количество']];

    res.push(["Меньше", medExpMin]);
    res.push(["Больше", medExpMax]);

    var data = google.visualization.arrayToDataTable(res);

    var options = {
        title: 'Стаж сотрудников',
        pieHole: 0.2,
    };

    var chart = new google.visualization.PieChart(document.getElementById('drawMedExp'));
    chart.draw(data, options);
}