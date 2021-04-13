import { useReducer } from "react";
import AuctionReducer from "./AuctionReducer";
import axios from 'axios';
import { LOAD_ALL_PROJECTS } from "../types";
import AuctionContext from "./AuctionContext";

const AuctionState = props => {

    const initialState = {
        project: null,
        allProjects: null,
        loading: true
    };

    const [ state, dispatch] = useReducer(AuctionReducer, initialState);

    const loadAllProject = async (clientId) => {

      await axios.get('api/project/client/' + clientId)
      .then((res) => {
        dispatch({
          type: LOAD_ALL_PROJECTS,
          payload: res.data,
        });
      })
      .catch((error) => {
        console.log(error);
      });
    };

    const saveProject = (project, isNew) => {

      if(isNew) {
        axios.post('/api/project', project)
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.log(error);
      })
      } else {
        axios.put('/api/project/' + project.projectId, project)
      .then((res) => {
        console.log(res);
      })
      .catch((error) => {
        console.log(error);
      })
      }
      

    }

    return (
      <AuctionContext.Provider
        value={{
        project: state.project,
        allProjects: state.allProjects,
        loading: state.loading,
        loadAllProject,
        saveProject
      }}
    >
      {props.children}
      </AuctionContext.Provider>
    )
};

export default AuctionState;