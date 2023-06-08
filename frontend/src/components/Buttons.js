import * as React from 'react';

export default function Buttons(props) {
  return (
    <div className="row">
      <div className="col-md-12 text-center" style={{ marginTop: '30px' }}>        
        <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.testReact}>
          Дополнительно
        </button>
        <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.magazines}>
          Все журналы
        </button>
        <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.allNews}>
          Новости
        </button>
        <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.myMagazines}>
          Понравившиеся журналы
        </button>
        <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.recommendations}>
          Рекомендации
        </button>
        <button  className="btn btn-primary" style={{ margin: '10px', marginLeft:'150px' }} onClick={props.login}>
          Вход
        </button>
        <button className="btn btn-dark" style={{ margin: '10px' }} onClick={props.logout}>
          Выход
        </button>
      </div>
    </div>
  );
};
