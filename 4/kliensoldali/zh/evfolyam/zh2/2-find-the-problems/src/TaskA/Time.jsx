export const Time = ({ showSeconds, time: { hour, minute, second } }) => {
  return (
    <>
      {hour}:{minute}
      {showSeconds && <>:{second}</>}
    </>
  );
};
