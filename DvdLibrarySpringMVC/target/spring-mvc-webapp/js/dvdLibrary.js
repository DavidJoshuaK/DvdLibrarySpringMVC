/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    // Add Button onclick handler
    $('#search-button').click(function (event) {

        $.ajax({
            type: 'POST',
            url: 'search/dvds',
            data: JSON.stringify({
                title: $('#search-title').val(),
                releaseYear: $('#search-releaseYear').val(),
                director: $('#search-director').val(),
                rating: $('#search-rating').val(),
                notes: $('#search-notes')
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            'dataType': 'json',
            success: function (data) {
                // clear errorMessages
                $('#errorMessages').empty();
                fillDvdTable(data);
            },
            error: function () {
                $('#errorMessages')
                        .append($('<li>')
                                .attr({class: 'list-group-item list-group-item-danger'})
                                .text('Error calling web service.  Please try again later.'));
            }
        });
    });
});

function fillDvdTable(data) {

    clearDvdTable();

    var contentRows = $('#contentRows');

    $.each(data, function (index, dvd) {
        var title = dvd.title;
        var releaseYear = dvd.releaseYear;
        var director = dvd.director;
        var rating = dvd.rating;
        var notes = dvd.notes;

        var row = '<tr>';
        row += '<td>' + title + '</td>';
        row += '<td>' + releaseYear + '</td>';
        row += '<td>' + director + '</td>';
        row += '<td>' + rating + '</td>';
        row += '<td>' + notes + '</td>';
        row += '</tr>';
        contentRows.append(row);
    });
}

function clearDvdTable() {
    $('#contentRows').empty();
}

