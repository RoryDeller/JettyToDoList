let address = 'http://localhost:8080';
let myList = '';
let newItem = '';

$(function() {
    $.post(address + '/get',
        function(data, status) {
            if (status === 'success') {
                populateList(data);
            }
        }
    );

    myList = $('#myList');
    newItem = $('#newItem');

});

function populateList(values) {
    myList.empty();
    for (let item of values.split(',')) {
        if (item !== '') {
            myList.append(`<li>${item} <input type="button" value="x" onclick="deleteItem('${item}')"/></li>`);
        }
    }
}

function addFirst() {
    $.post(address + '/add', 'first=' + newItem.val(),
        function(data, status) {
            if (status === 'success') populateList(data);
        }
    );
}

function addLast() {
    $.post(address + '/add', 'last=' + newItem.val(),
        function(data, status) {
            if (status === 'success') populateList(data);
        }
    );
}

function deleteItem(item) {
    $.post(address + '/delete', item,
        function(data, status) {
            if (status === 'success') populateList(data);
        }
    );
}

function clearList() {
    $.post(address + '/clear',
        function(data, status) {
            if (status === 'success') myList.empty();
        }
    );
}