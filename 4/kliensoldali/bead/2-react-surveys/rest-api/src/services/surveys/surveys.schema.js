// // For more information about this file see https://dove.feathersjs.com/guides/cli/service.schemas.html
import { resolve, virtual } from '@feathersjs/schema'
import { Type, getValidator, querySyntax } from '@feathersjs/typebox'
import { dataValidator, queryValidator } from '../../validators.js'
import { userSchema } from '../users/users.schema.js'
import { v4 as uuidv4 } from 'uuid'

// Main data model schema
export const surveySchema = Type.Object(
  {
    id: Type.Number(),
    name: Type.String(),
    content: Type.String(),
    hash: Type.String(),
    createdAt: Type.Number(),
    userId: Type.Number(),
    user: Type.Ref(userSchema)
  },
  { $id: 'Survey', additionalProperties: false }
)
export const surveyValidator = getValidator(surveySchema, dataValidator)
export const surveyResolver = resolve({
  user: virtual(async (survey, context) => {
    // Associate the user that sent the message
    return context.app.service('users').get(survey.userId)
  })
})

export const surveyExternalResolver = resolve({})

// Schema for creating new entries
export const surveyDataSchema = Type.Pick(surveySchema, ['name', 'content'], {
  $id: 'SurveyData'
})
export const surveyDataValidator = getValidator(surveyDataSchema, dataValidator)
export const surveyDataResolver = resolve({
  hash: async () => {
    return uuidv4()
  },
  userId: async (_value, _message, context) => {
    // Associate the record with the id of the authenticated user
    return context.params.user.id
  },
  createdAt: async () => {
    return Date.now()
  }
})

// Schema for updating existing entries
export const surveyPatchSchema = Type.Partial(surveySchema, {
  $id: 'SurveyPatch'
})
export const surveyPatchValidator = getValidator(surveyPatchSchema, dataValidator)
export const surveyPatchResolver = resolve({})

// Schema for allowed query properties
export const surveyQueryProperties = Type.Pick(surveySchema, [
  'id',
  'name',
  'content',
  'hash',
  'createdAt',
  'userId'
])
export const surveyQuerySchema = Type.Intersect(
  [
    querySyntax(surveyQueryProperties),
    // Add additional query properties here
    Type.Object({}, { additionalProperties: false })
  ],
  { additionalProperties: false }
)
export const surveyQueryValidator = getValidator(surveyQuerySchema, queryValidator)
export const surveyQueryResolver = resolve({
  userId: async (value, user, context) => {
    // We want to be able to find all surveys but
    // only let a user modify their own surveys otherwise
    if (context.params.user && context.method !== 'find') {
      return context.params.user.id
    }

    return value
  }
})
