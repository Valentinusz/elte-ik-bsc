import { BadRequest } from '@feathersjs/errors'

export const checkQuery = async (context) => {
  console.log(`Running hook checkQuery on ${context.path}.${context.method}`)
  if (!context.params.query.hash) {
    if (context.params.user) {
      if (!context.params.query.userId) {
        throw new BadRequest('userId is missing in query')
      }
      // console.log(typeof context.params.user.id)
      // console.log(typeof context.params.query.userId)
      if (parseInt(context.params.query.userId) !== context.params.user.id) {
        throw new BadRequest('Permission denied')
      }
    }
  }
}
