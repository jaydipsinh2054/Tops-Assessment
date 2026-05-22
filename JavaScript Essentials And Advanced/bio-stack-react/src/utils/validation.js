export function validateURL(url) {

  const regex = /^https:\/\/.+/;

  return regex.test(url);

}