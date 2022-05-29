const fs = require('fs');

const environmentFile = `export const environment = {
  baseApiUrl: '${process.env["AFN_BASE_API_URI"]}',
  production: '${process.env["PRODUCTION"]}'
};
`;

// Generate environment.ts file
fs.writeFile('./src/environments/environment.prod.ts', environmentFile, function (err: any) {
  if (err) {
    console.error(err);
  } else {
    console.log('Angular environment.ts file generated');
  }
});


/*
Run npm node set-env.js (or npm ts-node set-env.ts) to generate your file
*/
