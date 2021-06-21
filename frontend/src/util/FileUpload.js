export async function handleUploadSubmit(file, userDataPic) {
    console.log(userDataPic)
    try {
      const fileContentsBase64 = await readUploadedFileAsBase64(file)
      return fileContentsBase64.substr(fileContentsBase64.indexOf(',') + 1)
    } catch (e) {
      console.warn(e.message)
    }
}
  
function  readUploadedFileAsBase64(inputFile) {
    const temporaryFileReader = new FileReader()
    return new Promise((resolve, reject) => {
      temporaryFileReader.onerror = () => {
        temporaryFileReader.abort()
        reject(new DOMException('Problem parsing input file.'))
      }
      temporaryFileReader.onload = () => {
        resolve(temporaryFileReader.result)
      }
      temporaryFileReader.readAsDataURL(inputFile)
    })
}