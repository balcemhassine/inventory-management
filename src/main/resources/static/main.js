async function connect() {
let id = document.getElementById("floatingUsername").value;
let pass = document.getElementById("floatingPassword").value;

  let data = {	userId: id,
                password: pass }
  fetch('auth/login', {
    method: 'POST',
    headers: {
      'Accept': 'application/json, text/plain, */*',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then(res => res.json())
    .then(res => {
        if(res === true)  {
            location.replace("../")
            }
        else alert("Wrong Password")
    });
}

async function generatePdfAndCsv() {
  let data = {	filePath : "C:/Users/Balcem Hassine/Desktop/report" }
  fetch('/reporting/generateCsv', {
    method: 'POST',
    headers: {
      'Accept': 'application/json, text/plain, */*',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(data)
  }).then(res => res.json())
        .then(res => {
            if(res === true)  {
                location.replace("../")
                alert("File generated at: "+ data.filePath)
                }
        })
}

getAllProducts();


async function getAllProducts() {

  fetch('/inventory/', {
    method: 'GET',
    headers: {
      'Accept': 'application/json, text/plain, */*',
      'Content-Type': 'application/json'
    }
  }).then(res => res.json())
        .then(res => {
            renderProductTable(res)
        })
}


async function renderProductTable(productList) {
let table = ` <table class="table table-striped">
                         <table class="table">
                           <thead>
                           <tr>
                             <th scope="col">ID</th>
                             <th scope="col">Name</th>
                             <th scope="col">Qte</th>
                             <th scope="col">Price</th>
                             <th scope="col">Category</th>
                           </tr>
                           </thead>
                           <tbody>`;
for (let i = 0; i < productList.length; i++) {
      table += ` <tr>
          <td scope="row">${ productList[i].productId}</td>
          <td scope="row">${ productList[i].productName}</td>
          <td scope="row">${ productList[i].quantityInStock}</td>
          <td scope="row">${ productList[i].price}</td>
          <td scope="row">${ productList[i].category}</td>
          </tr>
      `
    }

    table += `           </tbody>
                          </table>
                        </table>`
  document.getElementById("productTableContainer").innerHTML = table

}
getAllTransactions()

async function getAllTransactions() {

  fetch('/transactions/', {
    method: 'GET',
    headers: {
      'Accept': 'application/json, text/plain, */*',
      'Content-Type': 'application/json'
    }
  }).then(res => res.json())
        .then(res => {
            renderTransactionTable(res)
        })
}

async function renderTransactionTable(transactionList) {
let table = ` <table class="table table-striped">
                         <table class="table">
                           <thead>
                           <tr>
                             <th scope="col">ID</th>
                             <th scope="col">Type</th>
                             <th scope="col">Qte</th>
                             <th scope="col">Date</th>
                             <th scope="col">Product Name</th>
                           </tr>
                           </thead>
                           <tbody>`;
for (let i = 0; i < transactionList.length; i++) {
      table += ` <tr>
          <td scope="row">${ transactionList[i].transactionId}</td>
          <td scope="row">${ transactionList[i].transactionType}</td>
          <td scope="row">${ transactionList[i].quantity}</td>
          <td scope="row">${ transactionList[i].date}</td>
          <td scope="row">${ transactionList[i].product.productName}</td>
          </tr>
      `
    }

    table += `           </tbody>
                          </table>
                        </table>`
  document.getElementById("transactionTableContainer").innerHTML = table

}


