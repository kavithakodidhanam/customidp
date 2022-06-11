import React from 'react'


const UsersFilter = ({ onClickFilterCallback, defaultKeyword }) => {
    const [keyword, setKeyword] = React.useState(defaultKeyword)
    const onKeywordChange = (e) => {
        setKeyword(e.target.value)
    }
    const onClickSearch = () => {
        onClickFilterCallback(keyword)
    }
    return (
        <div className="form__form-group">

            <div className="col-md-4">
            <span className="text-blue pointer"><b>Search By ProviderId</b></span>
                <div className="">
                    <input
                        value={keyword}
                        onChange={onKeywordChange}
                        type="text"
                        placeholder="Search users"
                    />
                </div>
            </div>
            <div className="col-md-4 d-flex align-items-center max-height-32px pl-1">
                <span className="text-blue pointer" onClick={onClickSearch}>Search</span>
            </div>
        </div>
    )
}

export default UsersFilter;
