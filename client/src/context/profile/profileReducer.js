import {
  ADD_EXPERIENCE,
  REMOVE_EXPERIENCE,
  UPDATE_EXPERIENCE,
  ADD_SKILL,
  REMOVE_SKILL,
  UPDATE_SKILL,
} from '../types';

const profileReducer = (state, action) => {
  switch (action.type) {
    case ADD_EXPERIENCE:
      return {
        ...state,
        experiences: [...state.experiences, action.payload],
      };
    case REMOVE_EXPERIENCE:
      return {
        ...state,
        experiences: state.experiences.filter(
          (s, sidx) => action.payload !== sidx
        ),
      };
    case ADD_SKILL:
      return {
        ...state,
        skills: [...state.skills, action.payload],
      };
    case REMOVE_SKILL:
      return {
        ...state,
        skills: state.skills.filter((s, sidx) => action.payload !== sidx),
      };

    default:
      return state;
  }
};

export default profileReducer;
