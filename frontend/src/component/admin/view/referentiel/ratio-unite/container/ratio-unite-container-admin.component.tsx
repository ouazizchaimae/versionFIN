import React from 'react';
import {KeyboardAvoidingView, Platform, SafeAreaView} from 'react-native';
import {globalStyle} from '../../../../../../shared/globalStyle';

import RatioUniteAdminTabNavigation from '../../../../../../navigation/admin/referentiel/RatioUniteAdminTabNavigation';

const RatioUniteAdmin = () => {
  return (
    <SafeAreaView style={globalStyle.myContainer}>

      <KeyboardAvoidingView
        behavior={Platform.OS === 'ios' ? 'padding' : 'height'}
        style={{ flex: 1 }} >
        <RatioUniteAdminTabNavigation />
      </KeyboardAvoidingView>
    </SafeAreaView>
  );
};

export default RatioUniteAdmin;
