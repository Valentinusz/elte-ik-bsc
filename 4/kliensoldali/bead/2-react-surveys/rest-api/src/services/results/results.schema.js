// // For more information about this file see https://dove.feathersjs.com/guides/cli/service.schemas.html
import { resolve, virtual } from '@feathersjs/schema'
import { Type, getValidator, querySyntax } from '@feathersjs/typebox'
import { dataValidator, queryValidator } from '../../validators.js'
import { surveySchema } from '../surveys/surveys.schema.js'
import { BadRequest } from '@feathersjs/errors'

// Main data model schema
export const resultSchema = Type.Object(
  {
    id: Type.Number(),
    content: Type.String(),
    createdAt: Type.Number(),
    surveyId: Type.Number(),
    survey: Type.Ref(surveySchema)
  },
  { $id: 'Result', additionalProperties: false }
)
export const resultValidator = getValidator(resultSchema, dataValidator)
export const resultResolver = resolve({
  survey: virtual(async (result, context) => {
    return context.app.service('surveys').get(result.surveyId)
  })
})

export const resultExternalResolver = resolve({})

// Schema for creating new entries
export const resultDataSchema = Type.Pick(resultSchema, ['content', 'surveyId'], {
  $id: 'ResultData'
})
export const resultDataValidator = getValidator(resultDataSchema, dataValidator)
export const resultDataResolver = resolve({
  createdAt: async () => {
    return Date.now()
  }
})

// Schema for updating existing entries
export const resultPatchSchema = Type.Partial(resultSchema, {
  $id: 'ResultPatch'
})
export const resultPatchValidator = getValidator(resultPatchSchema, dataValidator)
export const resultPatchResolver = resolve({})

// Schema for allowed query properties
export const resultQueryProperties = Type.Pick(resultSchema, ['id', 'content', 'createdAt', 'surveyId'])
export const resultQuerySchema = Type.Intersect(
  [
    querySyntax(resultQueryProperties),
    // Add additional query properties here
    Type.Object({}, { additionalProperties: false })
  ],
  { additionalProperties: false }
)
export const resultQueryValidator = getValidator(resultQuerySchema, queryValidator)
export const resultQueryResolver = resolve({
  surveyId: async (value, result, context) => {
    if (context.method === 'find' || context.method === 'get') {
      if (value === undefined) {
        throw new BadRequest('surveyId is missing in query')
      }
      // console.log(context.params.user.id)
      // console.log(await context.app.service('surveys').get(value).userId)
      if (context.params.user) {
        if ((await context.app.service('surveys').get(value)).userId !== context.params.user.id) {
          throw new BadRequest('survey does not belong to the authenticated user')
        }
      }
    }
    return value
  }
})
