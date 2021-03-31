import React, { useReducer } from 'react';
import ProfileContext from './profileContext';
import ProfileReducer from './profileReducer';
import {
  ADD_EXPERIENCE,
  REMOVE_EXPERIENCE,
  ADD_SKILL,
  REMOVE_SKILL,
} from '../types';

const ProfileState = (props) => {
  const initialState = {
    experiences: [],
    languages: '',
    skills: [],
  };

  const [state, dispatch] = useReducer(ProfileReducer, initialState);

  //Add Experience
  const addExperience = (experience) => {
    dispatch({ type: ADD_EXPERIENCE, payload: experience });
  };

  //Delete Experience
  const deleteExperience = (idx) => {
    dispatch({ type: REMOVE_EXPERIENCE, payload: idx });
  };

  //Update Experience

  //Add Skill
  const addSkill = (skill) => {
    dispatch({ type: ADD_SKILL, payload: skill });
  };

  //Delete Skill
  const deleteSkill = (idx) => {
    dispatch({ type: REMOVE_SKILL, payload: idx });
  };

  //Update Skill

  return (
    <ProfileContext.Provider
      value={{
        experiences: state.experiences,
        languages: state.languages,
        skills: state.skills,
        addExperience,
        deleteExperience,
        addSkill,
        deleteSkill,
      }}
    >
      {props.children}
    </ProfileContext.Provider>
  );
};

export default ProfileState;
